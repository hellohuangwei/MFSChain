package com.example.mfschain.data;

import java.util.List;

// Inner class to hold Merkle result (root hash and verification paths)
    public  class MerkleResult {
        private String rootHash;
        private List<List<String>> verificationPaths;

        public MerkleResult(String rootHash, List<List<String>> verificationPaths) {
            this.rootHash = rootHash;
            this.verificationPaths = verificationPaths;
        }

        public String getRootHash() {
            return rootHash;
        }

        public List<List<String>> getVerificationPaths() {
            return verificationPaths;
        }
    }