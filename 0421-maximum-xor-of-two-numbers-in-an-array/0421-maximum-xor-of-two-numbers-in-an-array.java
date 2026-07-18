class TrieNode {
    TrieNode left; // for 0
    TrieNode right; // for 1
}

class BitTrie {
    public void insert(TrieNode root, int num) {
        TrieNode ptr = root;
        // loop 31 se 0 tak hai coz 32 bit numbers constraints me given hai
        // sp for simplicity we are writing all the numbers in 32 bits in Trie
        for (int i=31; i>=0; i--) {
            int ith_bit = (num >> i) & 1; // num ke ith bit ko lsb position pe le aayega(0 pe) then usee & 1 karne par pata chalega ki wo bit set hai ya nahi (1 or 0). then uss basis pe insert karenge
            if (ith_bit == 1) {
                if (root.right == null) {
                    root.right = new TrieNode();
                } 
                root = root.right;
            } else {
                if (root.left == null) {
                    root.left = new TrieNode();
                }
                root = root.left;
            }
        }
    }

    public int findMaxXor(TrieNode root, int num) {
        TrieNode ptr = root;
        int maxXor = 0;
        for (int i=31; i>=0; i--) {
            int ith_bit = (num >> i) & 1; // same things mentioned above

            if (ith_bit == 1) {
                if (ptr.left != null) {
                    maxXor += (int) (Math.pow(2, i));
                    ptr = ptr.left;
                } else {
                    ptr = ptr.right;
                }
            } else {
                if (ptr.right != null) {
                    maxXor += (int) (Math.pow(2, i));
                    ptr = ptr.right;
                } else {
                    ptr = ptr.left;
                }
            }
        }

        return maxXor;
    }
}

class Solution {
    public int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();
        BitTrie trie = new BitTrie();

        for (int num : nums) {
            trie.insert(root, num);
        }

        int ans = 0;
        for (int i=0; i<nums.length; i++) {
            int temp = trie.findMaxXor(root, nums[i]);
            ans = Math.max(ans, temp);
        }

        return ans;
    }
}