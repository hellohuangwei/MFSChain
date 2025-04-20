# ⚓ MFSChain Cluster

**MFSChain Cluster** is the **first blockchain-based cluster** designed for the **efficient management of large-scale off-chain maritime data (MASS)**. Built with **Spring Boot**, it features modular components for consensus, P2P networking, real-time data sync, and decentralized maritime services.

> 🌐 Built for smart shipping — bridging vessel, port, and institutional data across borders and platforms.

---

## 📦 GitHub Repository

👉 [MFSChain GitHub](https://github.com/hellohuangwei/MFSChain.git)

---

## 🔧 Tech Stack

- ☕ **Java 17+**
- 🚀 **Spring Boot**
- 🌐 **WebSocket** for real-time sync
- 🛢 **Hibernate + MySQL** for off-chain data
- 📡 **Custom P2P Protocol** for node communication
- 🧠 **Aigle Consensus** — trust-based algorithm for dynamic node networks

---

## 📁 Project Structure

```plaintext
├── config           # Global configs and application setup
├── consensus        # Aigle consensus logic and trust voting
├── core             # Blockchain core components (chain, ledger, blocks)
├── crypto           # Hashing, signatures, Merkle tree
├── data             # Maritime data block structure & sync logic
├── network          # Node communication & status exchange
├── node             # Vessel identity, trust score, PoMST status
├── p2p              # Decentralized P2P network stack
├── rpc              # API interfaces (RESTful + RPC)
├── storage          # Persistent DB and block storage
├── test             # Unit and integration tests
└── MfsChainApplication.java  # Spring Boot main entry
```

---

## 🚀 Getting Started

### 🔍 Prerequisites

- Java 17+
- Maven 3.6+
- MySQL 8+
- Git

### ▶️ Run Locally

```bash
git clone https://github.com/hellohuangwei/MFSChain.git
cd MFSChain
mvn clean install
java -jar target/mfschain-0.0.1-SNAPSHOT.jar
```

---

## 🧠 Core Modules

### 🔗 `consensus`

- Implements **Aigle Consensus**
- Features trust-value voting
- Sampling, impact evaluation, clustering

### 🌐 `p2p`

- Node discovery and handshake
- Transaction/block broadcasting
- Peer status monitoring

### 📦 `data`

- Structured maritime data blocks
- GPS, velocity, timestamp support
- Merkle root and proof path generation

### 📡 `websocket`

- Real-time data & consensus sync
- Pushes block status updates
- Channel-based event handling

### 🧠 `core`

- Chain and block management
- Ledger validation
- Block proposal and verification

### 🔐 `crypto`

- SHA-256 hashing
- Merkle Tree computation
- Digital signature utilities

### 🧩 `node`

- Maintains node/vessel state
- Handles PoMST proof results
- Stores trust values & cluster grouping

### 📊 `rpc`

- Swagger-documented REST APIs
- External interfaces for data sync
- Includes block explorer endpoints

---

## 🧭 Aigle Consensus Flow

```
✅ Node registered
        ↓
🎯 Added to node pool (`AigleConsensusService`)
        ↓
📨 Receives transaction / block proposal
        ↓
🧠 startConsensus()
    ├── Initial sampling
    ├── Impact evaluation
    └── Clustering & trust-based vote
        ↓
✔️ Consensus threshold met
        ↓
📢 Broadcast result via WebSocket
        ↓
📦 All nodes confirm → New block construction begins
```

---

## 📡 WebSocket Endpoints

```bash
ws://localhost:8080/ws/consensus
ws://localhost:8080/ws/data-stream
```

Subscribe to:
- ✅ Consensus result notifications
- 📦 Block proposal status
- 👥 Node availability and heartbeat

---

## 🧪 Running Tests

```bash
mvn test
```

---

## 🧰 Example Use Cases

- ⚓ **Port Coordination**  
  Synchronize berth/traffic/cargo across stakeholders

- 🛰 **Satellite Data Leasing**  
  Provide secure encrypted channels for leasing and retrieval

- 🌦 **AIS + Weather Fusion**  
  Integrate real-time AIS and weather data for route optimization

---

## 🚧 Future Improvements

- 🔗 Ethereum-compatible data oracle module
- 🛡 Trusted Execution Environment (TEE) support
- 📊 Interactive dashboard for consensus & node metrics

---

## 📜 License

© 2025 Wei Huang — Shanghai Maritime University  
Distributed under a university-specific open source license.
