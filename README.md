
# ⚓ MFSChain Cluster

**MFSChain Cluster** is the **first blockchain-based cluster** tailored for the **efficient management of large-scale off-chain maritime data (MASS)**. This system is implemented using **Spring Boot**, with a modular design to support consensus, peer-to-peer networking, data synchronization, and decentralized data services.

> Built for smart shipping, MFSChain bridges vessel, port, and institutional data across borders and platforms.

---

## 🌐 Project Repository

📦 GitHub: [https://github.com/hellohuangwei/MFSChain.git](https://github.com/hellohuangwei/MFSChain.git)

---

## 🔧 Tech Stack

- **Java 17+**
- **Spring Boot**
- **WebSocket** for real-time sync
- **Hibernate + MySQL** for off-chain data storage
- **Custom P2P Protocol** for node communication
- **Aigle Consensus** for efficient and trust-based consensus in dynamic networks

---

## 📁 Project Structure

```
├── config           # System-wide configurations
├── consensus        # Aigle consensus algorithm and voting mechanics
├── core             # Core blockchain classes and chain management
├── crypto           # Cryptographic operations (hashing, Merkle Tree, signatures)
├── data             # Maritime data model and data block handling
├── network          # Network-level communication logic
├── node             # Node identity and validator management
├── p2p              # Peer-to-peer networking, broadcasting, handshake protocols
├── rpc              # RESTful and RPC APIs for external interaction
├── storage          # Persistent storage and off-chain DB sync logic
├── test             # Unit and integration tests
└── MfsChainApplication.java  # Spring Boot main entry point
```

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- MySQL 8+ (used for off-chain storage)
- Git

### Build and Run

```bash
git clone https://github.com/hellohuangwei/MFSChain.git
cd MFSChain
mvn clean install
java -jar target/mfschain-0.0.1-SNAPSHOT.jar
```

---

## 🌊 Main Modules

### 🔗 consensus

Implements the **Aigle consensus algorithm**, allowing nodes to reach agreement through trust metrics, clustering, and voting strategies.

### 🌍 p2p

Manages peer-to-peer communication and decentralized networking between nodes. Includes broadcasting, message routing, and handshake protocol.

### 📦 data

Handles maritime data blocks (position, speed, timestamps), Merkle trees, and sync status between nodes.

### 📡 websocket

Supports **real-time synchronization** of maritime data via WebSocket channels. Pushes block status and consensus state updates across all listeners.

### 🧠 core

Blockchain core components such as ledger management, chain validator, and block appending logic.

### 🔐 crypto

Provides essential cryptographic utilities: hashing, digital signatures, and Merkle tree generation.

### 🧩 node

Maintains vessel/node identities, their trust scores, PoMST results, and clustering behaviors.

### 📊 rpc

REST + RPC interface for external apps to submit data, query consensus results, and monitor sync status.

---

## 📡 WebSocket Usage

WebSocket endpoints are exposed under:

```
ws://localhost:8080/ws/consensus
ws://localhost:8080/ws/data-stream
```

Use these to subscribe to:
- Cluster consensus status updates
- Real-time data acceptance/rejection
- Node heartbeat and presence changes

---

## 🧪 Testing

```bash
mvn test
```

---

## 📌 Example Use Cases

- **Port Coordination**: Real-time synchronization of berth, traffic, and cargo data
- **Satellite Data Leasing**: Encrypted satellite-based services via smart routing
- **AIS + Weather Fusion**: Combine real-time AIS and met-ocean data with cryptographic validation

---

## 🛠️ Future Work

- Integration with on-chain Ethereum data market
- Trusted execution environment (TEE) support
- Visual dashboard for consensus visualization

---

## 📄 License

shagnhai Maritime university License © 2025 Wei Huang
