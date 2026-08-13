class Solution {
    // Custom node to store segment tree states
    class Node {
        int maxLen;      // Max length of repeating char in this segment
        int prefixLen;   // Length of repeating char at the start (prefix)
        int suffixLen;   // Length of repeating char at the end (suffix)
        int len;         // Total length of this segment
        char prefixChar; // Character of the prefix
        char suffixChar; // Character of the suffix
    }

    Node[] tree;
    char[] arr;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        arr = s.toCharArray();
        tree = new Node[4 * n + 1]; // 4*N is standard size for segment tree
        
        // Build the initial segment tree
        build(1, 0, n - 1);
        
        int k = queryCharacters.length();
        int[] ans = new int[k];
        
        // Process each query
        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = tree[1].maxLen; // The root node always holds the max length for the whole string
        }
        
        return ans;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node();
            tree[node].maxLen = 1;
            tree[node].prefixLen = 1;
            tree[node].suffixLen = 1;
            tree[node].len = 1;
            tree[node].prefixChar = arr[start];
            tree[node].suffixChar = arr[start];
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, char c) {
        // Leaf node reached, update the character
        if (start == end) {
            tree[node].prefixChar = c;
            tree[node].suffixChar = c;
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, c);
        } else {
            update(2 * node + 1, mid + 1, end, idx, c);
        }
        // Update the current node by merging left and right children
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private Node merge(Node left, Node right) {
        Node res = new Node();
        res.len = left.len + right.len;
        res.prefixChar = left.prefixChar;
        res.suffixChar = right.suffixChar;
        
        // Calculate new prefix length
        res.prefixLen = left.prefixLen;
        if (left.prefixLen == left.len && left.prefixChar == right.prefixChar) {
            res.prefixLen += right.prefixLen;
        }
        
        // Calculate new suffix length
        res.suffixLen = right.suffixLen;
        if (right.suffixLen == right.len && right.suffixChar == left.suffixChar) {
            res.suffixLen += left.suffixLen;
        }
        
        // Max length is either in the left child, right child, or straddling the middle
        res.maxLen = Math.max(left.maxLen, right.maxLen);
        if (left.suffixChar == right.prefixChar) {
            res.maxLen = Math.max(res.maxLen, left.suffixLen + right.prefixLen);
        }
        
        return res;
    }
}