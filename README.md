
# ⚓ MFSChain Cluster

**MFSChain Cluster** is the first **blockchain cluster** designed for the **efficient management of large-scale off-chain data in MASS (Maritime Autonomous Surface Ships)**.  
It is built entirely using **Spring Boot**, offering an enterprise-grade, modular, and extensible backend framework for maritime blockchain infrastructure.

> 🔗 GitHub: [https://github.com/hellohuangwei/MFSChain.git](https://github.com/hellohuangwei/MFSChain.git)

---

## 🌊 Overview

Modern maritime systems require secure, scalable, and interoperable data platforms for:

- Autonomous navigation coordination  
- Cross-border port and vessel communication  
- Secure storage and verification of sensor and route data  
- Consensus among distributed maritime nodes  

**MFSChain Cluster** addresses these challenges with a Spring Boot–powered blockchain node cluster capable of:

- ✅ Running **Aigle consensus** for secure and trust-based voting  
- 🧱 Managing multi-role nodes (vessels, ports, providers)  
- 🛰 Syncing on-chain and off-chain data in real time  
- 🌐 Providing robust REST and WebSocket interfaces  

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- MySQL 8.x+
- Git

### Clone the Repository

```bash
git clone https://github.com/hellohuangwei/MFSChain.git
cd MFSChain
```

### Configuration

Edit `application.yml` for your local environment:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mfschain
    username: root
    password: your_password
server:
  port: 8080
```

### Build & Run

```bash
mvn clean install
java -jar target/mfschain-0.0.1-SNAPSHOT.jar
```

---

## 🧩 Main Modules

| Module                  | Description                                                                 |
|-------------------------|-----------------------------------------------------------------------------|
| `MaritimeNodeService`   | Manages registration, metadata, and trust levels of nodes                   |
| `AigleConsensusService` | Executes Aigle consensus across nodes based on impact and votes             |
| `MaritimeDataService`   | Handles uploading, verifying, and Merkle-hashing maritime data              |
| `WebSocketService`      | Pushes real-time consensus and data sync events to subscribed clients       |
| `DataSyncService`       | Connects blockchain smart contracts to off-chain systems for event logging  |
| `NodeController`        | RESTful APIs for node control and consensus triggering                     |

---

## 📡 WebSocket Realtime Sync

**MFSChain Cluster** uses WebSocket to deliver live updates to UIs and systems.

- Endpoint: `ws://localhost:8080/ws/consensus`
- Topics:
  - `/topic/consensus/progress` — Tracks Aigle consensus stages
  - `/topic/data/accepted` — Notifies when data is accepted
  - `/topic/node/status` — Node join/leave and status changes

Clients connect via STOMP WebSocket protocol.

---

## 📜 Swagger API Documentation

Visit:  
🔗 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Sample endpoints:

| Method | Endpoint                      | Description                             |
|--------|-------------------------------|-----------------------------------------|
| `POST` | `/api/nodes/register`         | Register a new node                     |
| `GET`  | `/api/nodes/all`              | List all registered nodes               |
| `POST` | `/api/consensus/start`        | Start Aigle consensus                   |
| `POST` | `/api/data/upload`            | Upload new maritime data block          |

---

## 🧪 Testing

Run backend tests with:

```bash
mvn test
```

Covers:

- Node onboarding and role validation  
- Cluster consensus behavior under voting thresholds  
- Data storage and trust-level adjustments  
- WebSocket message delivery simulation  

---

## 🌐 Use Cases

- ✅ **Autonomous ship coordination** via on-chain trust signals  
- 🔄 **Vessel ↔ Port sync** using PoMST and cluster consensus  
- 📡 **Sensor data validation** with Merkle proof in mass ship systems  
- 🤝 **Cross-org data sharing** verified and pushed via WebSocket  

---

## 🛠 Directory Structure

```
src/
 ├── controller/        # REST endpoints
 ├── websocket/         # WebSocket config + push handlers
 ├── consensus/         # Aigle algorithm logic
 ├── data/              # JPA entities (Node, DataBlock, Merkle, etc.)
 ├── service/           # Core business services
 ├── repository/        # Database operations
 └── MfschainApplication.java
```

---

## 📈 Roadmap

- [x] Node voting and impact analysis
- [x] Aigle consensus with trust adjustment
- [x] Real-time WebSocket communication
- [x] Swagger3 OpenAPI documentation
- [ ] Integration with `MaritimeDataRouter` contract
- [ ] Role-based access via JWT
- [ ] Deployment via Docker + Kubernetes

---

## 📜 License

MIT License
