# MaritimeDataRouter Contract

The `MaritimeDataRouter` is a smart contract built for decentralized **maritime data exchange**, allowing ships, ports, and data providers to submit, cancel, and match encrypted data orders.

> ⚠️ This contract is built on top of `MaritimeDataMarketCore`, which must be deployed prior to using this router.

---

## Quick Start

To get started with deploying and interacting with the `MaritimeDataRouter`, follow the steps below:

### Prerequisites

- Node.js v16+
- [Hardhat](https://hardhat.org)
- MetaMask or any Ethereum wallet (for deployment on testnet)
- Solidity version: `>=0.7.0 <0.9.0`

### 1. Clone the Repository

```bash
git clone https://github.com/your-org/maritime-data-exchange.git
cd maritime-data-exchange
```

### 2. Install Dependencies

```bash
npm install
```

---

## Deployment

### Hardhat Localnet Example

1. Compile the contract:

```bash
npx hardhat compile
```

2. Run a local testnet:

```bash
npx hardhat node
```

3. Deploy the contract:

```bash
npx hardhat run scripts/deploy.js --network localhost
```

### `scripts/deploy.js` Example

```javascript
async function main() {
  const [deployer] = await ethers.getSigners();

  console.log("Deploying contracts with:", deployer.address);

  const MarketCore = await ethers.getContractFactory("MaritimeDataMarketCore");
  const marketCore = await MarketCore.deploy();
  await marketCore.deployed();

  const Router = await ethers.getContractFactory("MaritimeDataRouter");
  const router = await Router.deploy();
  await router.deployed();

  console.log("MaritimeDataRouter deployed to:", router.address);
}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
```

---

## Testing

### Run Contract Tests

```bash
npx hardhat test
```

### `test/MaritimeDataRouter.test.js` Example

```javascript
const { expect } = require("chai");

describe("MaritimeDataRouter", function () {
  let router, owner, addr1;

  beforeEach(async function () {
    [owner, addr1] = await ethers.getSigners();

    const MarketCore = await ethers.getContractFactory("MaritimeDataMarketCore");
    const core = await MarketCore.deploy();
    await core.deployed();

    const Router = await ethers.getContractFactory("MaritimeDataRouter");
    router = await Router.deploy();
    await router.deployed();
  });

  it("Should allow the owner to set a fee receiver", async function () {
    await router.setFeeTo(addr1.address);
    expect(await router.feeTo()).to.equal(addr1.address);
  });

  it("Should allow order submission", async function () {
    const order = {
      maker: addr1.address,
      salt: 1234,
      listingTime: Math.floor(Date.now() / 1000),
      expirationTime: Math.floor(Date.now() / 1000) + 3600,
      offer: true,
    };

    await expect(
      router.connect(addr1).submitMaritimeOrder(
        order.maker,
        order.salt,
        order.listingTime,
        order.expirationTime,
        order.offer,
        "0x", "0x", "0x", "0x"
      )
    ).to.not.be.reverted;
  });
});
```

---

## Related Repositories

- [MaritimeDataMarketCore](https://github.com/your-org/maritime-data-core)
- [IBC for Maritime](https://github.com/your-org/maritime-ibc-module)

---


