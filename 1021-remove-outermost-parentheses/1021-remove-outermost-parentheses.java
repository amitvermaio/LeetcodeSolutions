class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();

        int open = 0;
        int close = 0;

        int firstOpenBrac = -1;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
                sb.append('(');

                if (firstOpenBrac == -1) 
                    firstOpenBrac = sb.length()-1;

            } else {
                close++;

                if (close == open) {
                    sb.deleteCharAt(firstOpenBrac);

                    open = 0;
                    close = 0;

                    firstOpenBrac = -1;
                } else {
                    sb.append(')');
                }
            }
        }

        return sb.toString();
    }
}