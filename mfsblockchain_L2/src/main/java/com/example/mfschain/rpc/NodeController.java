//package com.example.mfschain.rpc;
//
//import com.example.mfschain.core.MaritimeNodeService;
//import com.example.mfschain.data.MaritimeNode;
//
//import com.example.mfschain.p2p.P2PService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/nodes")
//public class NodeController {
//
//    @Autowired
//    private MaritimeNodeService maritimeNodeService;
//
//    @Autowired
//    private P2PService p2PService;
//
//    // 注册一个新的节点
//    @PostMapping("/register")
//    public MaritimeNode registerNode(@RequestBody MaritimeNode node) {
//        return maritimeNodeService.registerNode(node);
//    }
//
//    // 获取所有节点信息
//    @GetMapping("/")
//    public List<MaritimeNode> getAllNodes() {
//        return maritimeNodeService.getAllNodes();
//    }
//
//    // 广播区块到所有节点
//    @PostMapping("/broadcast")
//    public String broadcastBlock(@RequestBody String blockData) {
//        p2PService.broadcastBlock(blockData);
//        return "Broadcast initiated";
//    }
//}
