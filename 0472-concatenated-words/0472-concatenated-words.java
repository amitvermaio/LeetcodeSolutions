class TrieNode {
    TrieNode[] children;
    boolean isEnd;
    public TrieNode() {
        children = new TrieNode[26];
        isEnd = false;
    }
}

class Trie {
    public void insert(TrieNode root, String word) {
        TrieNode ptr = root;
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);

            if (ptr.children[ch - 'a'] == null) {
                ptr.children[ch - 'a'] = new TrieNode();
            }
            ptr = ptr.children[ch - 'a'];
        }
        ptr.isEnd = true;
    }

    public boolean isConcatenatedWord(TrieNode root, TrieNode ptr, String word, int i, int count, Boolean[] memo) { 
        if (i == word.length()) return false;
        
        // Hum sirf tab memoize karenge jab ek naya word dictionary se start ho raha ho (ptr == root)
        boolean isStartingNewWord = (ptr == root);
        
        if (isStartingNewWord && memo[i] != null) {
            return memo[i]; 
        }

        char ch = word.charAt(i);
        
        if (ptr.children[ch - 'a'] == null) {
            if (isStartingNewWord) memo[i] = false; 
            return false;
        }
        ptr = ptr.children[ch - 'a'];

        if (i == word.length()-1) {
            boolean isValid = count > 1 && ptr.isEnd;
            if (isStartingNewWord) memo[i] = isValid;
            return isValid;
        }

        boolean res = false;
        if (ptr.isEnd) {
            res = isConcatenatedWord(root, root, word, i+1, count+1, memo); 
        }

        boolean finalRes = res || isConcatenatedWord(root, ptr, word, i+1, count, memo); 
        
        if (isStartingNewWord) {
            memo[i] = finalRes; 
        }
        
        return finalRes;
    }
}

class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Trie trie = new Trie();
        TrieNode root = new TrieNode();
        for (String word : words) {
            trie.insert(root, word);
        }

        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (word.isEmpty()) continue;
            if (trie.isConcatenatedWord(root, root, word, 0, 1, new Boolean[word.length()])) {
                ans.add(word);
            }
        }

        return ans;
    }
}