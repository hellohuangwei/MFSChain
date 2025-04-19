package com.example.mfschain.rpc;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blocks")
public class BlockController {

    // 接收并处理从其他节点广播过来的区块
    @PostMapping("/receive")
    public String receiveBlock(@RequestBody String blockData) {
        System.out.println("Received Block Data: " + blockData);
        // 这里可以进一步处理接收到的区块数据，比如将其存储到区块链中
        return "Block received";
    }
}
