package com.example.mfschain.p2p;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class P2PService {

    private final RestTemplate restTemplate;

    public P2PService() {
        this.restTemplate = new RestTemplate();
    }

    // 广播区块到所有船舶节点
    public void broadcastBlock(String blockData) {
        // 假设我们从数据库获取所有节点的 URL
        String[] nodeUrls = {"http://localhost:8081", "http://localhost:8082"};  // 示例节点 URL

        for (String nodeUrl : nodeUrls) {
            // 向每个船舶节点广播区块数据
            try {
                restTemplate.postForObject(nodeUrl + "/api/blocks/receive", blockData, String.class);
            } catch (Exception e) {
                System.out.println("Error broadcasting to node: " + nodeUrl);
            }
        }
    }
}
