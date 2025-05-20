package com.example.mfschain.p2p;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class P2PService {

    private final RestTemplate restTemplate;

    public P2PService() {
        this.restTemplate = new RestTemplate();
    }

    // Broadcast block to all ship nodes
    public void broadcastBlock(String blockData) {
        // Assume we get all node URLs from the database
        String[] nodeUrls = {"http://localhost:8081", "http://localhost:8082"};  // Example node URLs

        for (String nodeUrl : nodeUrls) {
            // Broadcast block data to each ship node
            try {
                restTemplate.postForObject(nodeUrl + "/api/blocks/receive", blockData, String.class);
            } catch (Exception e) {
                System.out.println("Error broadcasting to node: " + nodeUrl);
            }
        }
    }
}
