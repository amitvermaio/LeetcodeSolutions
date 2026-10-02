class Solution {
    void solve(List<String> ans, String build, int n, int open, int close) {
        if (build.length() == 2*n) {
            ans.add(build);
            return;
        }

        if (open < n) 
            solve(ans, build + '(', n, open+1, close);
        if (close < open)
            solve(ans, build + ')', n, open, close+1);
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        solve(ans, "", n, 0, 0);
        return ans;
    }
}