class Solution {
    int n;
    Set<String> res;
    int maxLen = 0;

    void solve(String s, StringBuilder curr, int i, int open) {
        if (open < 0) return;

        if (i == n) {
            if (open == 0) {
                String str = curr.toString();
                if (str.length() > maxLen) {
                    maxLen = str.length();
                    res.clear();
                    res.add(str);
                } else if (str.length() == maxLen) {
                    res.add(str);
                }
            }
            return;
        }

        char c = s.charAt(i);

        if (c != '(' && c != ')') {
            curr.append(c);
            solve(s, curr, i + 1, open);
            curr.deleteCharAt(curr.length() - 1);
            return;
        }

        // skip parenthesis
        solve(s, curr, i + 1, open);

        // keep parenthesis
        curr.append(c);
        solve(s, curr, i + 1, open + (c == '(' ? 1 : -1));
        curr.deleteCharAt(curr.length() - 1);
    }

    public List<String> removeInvalidParentheses(String s) {
        res = new HashSet<>();
        n = s.length();
        solve(s, new StringBuilder(), 0, 0);
        return new ArrayList<>(res);
    }
}