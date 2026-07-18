class TrieNode {
    TrieNode[] children;
    boolean isEnd = false;
    String word;
    public TrieNode() {
        children = new TrieNode[26];
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

        ptr.word = word;
        ptr.isEnd = true;
    }

    public String findRootWord(TrieNode root, String word) {
        TrieNode ptr = root;

        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);

            if (ptr.children[ch - 'a']==null || ptr.isEnd) {
                break;
            }

            ptr = ptr.children[ch - 'a'];
        }

        return ptr.isEnd ? ptr.word : word;
    }
}

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        TrieNode root = new TrieNode();
        Trie trie = new Trie();

        for (String word : dictionary) {
            trie.insert(root, word);
        } 

        String[] words = sentence.split("\\s+");
        for (int i=0; i<words.length; i++) {
            words[i] = trie.findRootWord(root, words[i]);
        }

        StringBuilder sb = new StringBuilder();
        
        for (int i=0; i<words.length; i++) {
            sb.append(words[i]);
            if (i != words.length-1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
}