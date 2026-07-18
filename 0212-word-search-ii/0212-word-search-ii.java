class TrieNode {
    String word;
    boolean endOfWord;
    TrieNode[] children;

    public TrieNode() {
        children = new TrieNode[26];
    }
}

class Trie {
    public TrieNode getNode() { // returns a new TrieNode instance for childrens
        TrieNode newNode = new TrieNode();
        newNode.word = "";
        newNode.endOfWord = false;

        return newNode;
    }

    public void insert(TrieNode root, String word) {
        TrieNode crawler = root;
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);

            if (crawler.children[ch - 'a'] == null) {
                crawler.children[ch - 'a'] = getNode();
            }
            crawler = crawler.children[ch - 'a'];
        }

        crawler.endOfWord = true;
        crawler.word = word;
    }
}

class Solution {
    int m, n;
    List<String> ans;
    public List<String> findWords(char[][] board, String[] words) {
        m = board.length;
        n = board[0].length;
        ans = new ArrayList<>();

        Trie trie = new Trie();
        TrieNode root = trie.getNode();

        for (String word : words) {
            trie.insert(root, word);
        }

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                char ch = board[i][j];
                if (root.children[ch - 'a'] != null) {
                    dfs(board, root, i, j);
                }
            }
        }

        return ans;
    }

    void dfs(char[][] board, TrieNode root, int r, int c) {
        char ch = board[r][c];
        root = root.children[ch - 'a'];

        if (root.endOfWord) {
            ans.add(root.word);
            root.endOfWord = false; // to avoid duplication;
        } // return nahi kr rhe coz it could be possible that these kinda words exits in this -> oat, oath. So h is still in the way

        board[r][c] = '$';
        
        int[] DIR = {0, -1, 0, 1, 0};
        for (int i=0; i<4; i++) {
            int nr = r + DIR[i];
            int nc = c + DIR[i+1];

            if (nr>=0&& nr<m && nc>=0 && nc<n && board[nr][nc]!='$') {
                char next = board[nr][nc];

                if (root.children[next - 'a'] != null) {
                    dfs(board, root, nr, nc);
                }
            }
        }

        board[r][c] = ch;
    }
}