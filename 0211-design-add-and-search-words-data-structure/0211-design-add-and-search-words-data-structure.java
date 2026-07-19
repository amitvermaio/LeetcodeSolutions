class TrieNode {
    TrieNode[] children;
    boolean isEnd = false;
    public TrieNode() {
        children = new TrieNode[26];
    }
}

class WordDictionary {
    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();    
    }
    
    public void addWord(String word) {
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
    
    public boolean search(String word) {
        return dfs(root, word, 0);
    }

    private boolean dfs(TrieNode ptr, String word, int i) {
        if (ptr == null) return false;

        if (i == word.length()) return ptr.isEnd;

        char ch = word.charAt(i);

        if (ch != '.') {
            return dfs(ptr.children[ch - 'a'], word, i+1);
        } 
            
        for (int j=0; j<26; j++) {
            if (dfs(ptr.children[j], word, i+1)) {
                return true;
            }
        }

        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */