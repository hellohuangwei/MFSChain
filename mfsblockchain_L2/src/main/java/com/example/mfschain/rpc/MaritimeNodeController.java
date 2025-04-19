package com.example.mfschain.rpc;

import com.example.mfschain.core.MaritimeNodeService;
import com.example.mfschain.data.MaritimeNode;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "maritime node manger：", description = "")
@RestController
@RequestMapping("/api/maritime-nodes")
public class MaritimeNodeController {

    final static private MaritimeNodeService maritimeNodeService = new MaritimeNodeService();

    /**
     * Register a new MaritimeNode.
     *
     * @param node The MaritimeNode object to be saved.
     * @return The saved MaritimeNode object.
     */
    @PostMapping("/register")
    public Boolean registerNode(@RequestBody MaritimeNode node) {
//        maritimeNodeService.save(node);
        System.out.println(23);
        return true;//        return maritimeNodeService.saveNode(node);
    }

    /**
     * Get a MaritimeNode by its ID.
     *
     * @param id The ID of the MaritimeNode.
     * @return The MaritimeNode object.
     */
    @GetMapping("/{id}")
    public Optional<MaritimeNode> getNodeById(@PathVariable int id) {
        return maritimeNodeService.getNodeById(id);
    }

    /**
     * Get all MaritimeNodes.
     *
     * @return A list of all MaritimeNode objects.
     */
    @GetMapping("/all")
    public List<MaritimeNode> getAllNodes() {
        return maritimeNodeService.getAllNodes();
    }


    /**
     * Update the status of a node.
     *
     * @param id     The ID of the MaritimeNode.
     * @param status The new status to set.
     */
    @PutMapping("/update-status/{id}")
    public void updateNodeStatus(@PathVariable int id, @RequestParam String status) {
        maritimeNodeService.updateNodeStatus(id, status);
    }

    /**
     * Delete a MaritimeNode by its ID.
     *
     * @param id The ID of the MaritimeNode to delete.
     */
    @DeleteMapping("/delete/{id}")
    public void deleteNode(@PathVariable int id) {
        maritimeNodeService.deleteNode(id);
    }
}
