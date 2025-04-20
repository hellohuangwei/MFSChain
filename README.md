# ⚓ MFSChain Cluster

**MFSChain Cluster** is the **first blockchain-based cluster** tailored for the **efficient management of large-scale off-chain maritime data (MASS)**. This system is implemented using **Spring Boot**, featuring a modular architecture to support:

- 🔄 Aigle consensus algorithm  
- 🌐 Peer-to-peer node networking  
- 🧩 WebSocket-based communication  
- ⚙️ Decentralized maritime data synchronization and validation  

> Built for smart shipping, MFSChain bridges vessel, port, and institutional data across borders and platforms.

---

## 🌐 Project Repository

📦 GitHub: [https://github.com/hellohuangwei/MFSChain.git](https://github.com/hellohuangwei/MFSChain.git)

---

## 🧠 Aigle Consensus Flow

Node successfully registered  
   ↓  
Added to the node pool (managed by `AigleConsensusService`)  
   ↓  
Receives a transaction / block proposal (e.g., new data block or transaction request)  
   ↓  
Executes `startConsensus()` which includes:  
    • Initial sampling  
    • Impact evaluation  
    • Clustering and trust-based voting synchronization  
   ↓  
Once sampling results meet consensus threshold  
   ↓  
✅ Consensus reached → Broadcast the result to all nodes via WebSocket  
   ↓  
📦 All nodes confirm → Begin constructing a new block on the chain  

---

## 🔌 WebSocket Node Communication

MFSChain uses Spring WebSocket + STOMP for real-time consensus broadcasting among nodes. The broadcasting service pushes consensus results to `/topic/consensus`, and all nodes subscribe to receive new block status or voting results.

```java
// Server-side broadcasting example
messagingTemplate.convertAndSend("/topic/consensus", "Node X reached consensus on new block.");
```

Nodes can subscribe using STOMP:

```js
stompClient.subscribe('/topic/consensus', function (message) {
    console.log("Consensus result: ", message.body);
});
```

---

## 🧩 Module Structure Overview

Below is an overview of the key modules:

```
MFSChain
├── consensus                # Aigle consensus service
├── p2p                     # Node discovery and peer-to-peer management
├── websocket               # WebSocket broadcasting module
├── data                    # Maritime data services and block validation
├── api                     # RESTful APIs (Swagger supported)
├── storage                 # Block data storage, caching and cleanup
├── config                  # Spring Boot configuration and annotations
```

---

## 📘 Tech Stack & Features

- ☕ Spring Boot microservices architecture
- 📡 Real-time WebSocket-based node broadcasting
- 📊 Aigle consensus algorithm (multi-round voting + trust mechanism)
- 🧱 Merkle tree for data integrity verification
- 🛰️ Dynamic node registration and participation
- 🐳 Docker-ready for fast deployment

---

## 👨‍💻 Developer Info

**Author**: Wei Huang  
**Organization**: Shanghai Maritime University  
📫 GitHub: [https://github.com/hellohuangwei](https://github.com/hellohuangwei)

---

> 📌 _MFSChain Cluster empowers the next generation of maritime data governance through decentralized, trustworthy, and scalable blockchain infrastructure._
