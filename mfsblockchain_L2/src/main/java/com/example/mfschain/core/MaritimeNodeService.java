package com.example.mfschain.core;

import com.example.mfschain.data.MaritimeNode;
import com.example.mfschain.data.MaritimeNodeRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaritimeNodeService  {


    @Autowired
    private MaritimeNodeRepository maritimeNodeRepository;


    /**
     * Save a MaritimeNode to the database.
     *
     * @param node The MaritimeNode object to be saved.
     * @return The saved MaritimeNode object.
     */
    public MaritimeNode saveNode(MaritimeNode node) {
        return maritimeNodeRepository.save(node);
    }

    /**
     * Get a MaritimeNode by its ID.
     *
     * @param id The ID of the MaritimeNode.
     * @return The MaritimeNode if found, otherwise null.
     */
    public Optional<MaritimeNode> getNodeById(int id) {
        return maritimeNodeRepository.findById(id);
    }

    /**
     * Get all MaritimeNodes.
     *
     * @return A list of all MaritimeNode objects.
     */
    public List<MaritimeNode> getAllNodes() {
        return maritimeNodeRepository.findAll();
    }


    /**
     * Update the status of a node.
     *
     * @param id     The ID of the MaritimeNode.
     * @param status The new status to set.
     */
    public void updateNodeStatus(int id, String status) {
        Optional<MaritimeNode> node = maritimeNodeRepository.findById(id);
        node.ifPresent(n -> {
            n.setStatus(status);
            maritimeNodeRepository.save(n);
        });
    }

    /**
     * Delete a MaritimeNode by its ID.
     *
     * @param id The ID of the MaritimeNode to delete.
     */
    public void deleteNode(int id) {
        maritimeNodeRepository.deleteById(id);
    }
}
