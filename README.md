
# MaritimeDataRouter Contract

`MaritimeDataRouter` 是一个面向 **海事数据交互** 场景的智能合约，旨在构建去中心化的船岸数据共享市场。该合约允许**船舶、港口、航运公司、数据服务商**等多元参与方提交、撮合和取消经过加密的数据交易订单，从而实现跨组织、跨区域的可信海事数据交互。

> ⚠️ 该路由合约依赖于底层合约 `MaritimeDataMarketCore`，部署和调用前必须先部署核心市场合约。

## 背景与价值

当前海事行业面临如下挑战：

- 船舶设备产出数据多样且孤立，难以统一接入
- 港口之间缺乏可信协同机制，数据交互依赖人工对接
- 数据提供方难以通过加密方式进行安全流通和获利
- 缺少可验证的订单协议和链上仲裁机制

本合约作为“**去中心化海事数据市场协议**”的关键调度入口，支持多边主体以可组合的方式进行数据挂单、撮合、加密验证和费用分发，从而构建 **跨国、跨平台的智能航运协作网络**。

## Quick Start

### Prerequisites

- Node.js v16+
- Hardhat
- MetaMask 或任何兼容的以太坊钱包
- Solidity 版本：>=0.7.0 <0.9.0

### 1. 克隆仓库

```bash
git clone https://github.com/your-org/maritime-data-exchange.git
cd maritime-data-exchange
```

### 2. 安装依赖

```bash
npm install
```

## 部署指南

1. 编译合约：

```bash
npx hardhat compile
```

2. 启动本地测试链：

```bash
npx hardhat node
```

3. 部署合约：

```bash
npx hardhat run scripts/deploy.js --network localhost
```

部署脚本（scripts/deploy.js）示例：

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

## 合约功能概览

- `submitMaritimeOrder`：提交加密的海事数据订单（出售/需求）
- `cancelMaritimeOrder`：取消未成交订单
- `matchMaritimeOrders`：撮合两个订单，自动验证并完成费用分发
- `setFeeTo`：设置费用接收地址

## 测试用例

执行以下命令运行测试：

```bash
npx hardhat test
```

测试文件（test/MaritimeDataRouter.test.js）示例：

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

## 应用场景示例

- 港口对港口：如宁波港与新加坡港之间实时追踪货物流信息
- 船舶航线调度：接入天气、AIS 轨迹数据实现链上验证与定价
- 空间卫星数据租用：数据提供商可设置权限、自动获益
- 货代订单自动撮合：挂单撮合可自动执行合约完成交易

