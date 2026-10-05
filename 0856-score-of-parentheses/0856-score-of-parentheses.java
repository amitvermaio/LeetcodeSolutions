class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        st.push(0);

        // (()(())) -> 6
        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                st.push(0);
            } else {
                int curr = st.pop();

                if (curr == 0) {
                    // ()
                    curr = 1;
                } else {
                    // (A)
                    curr = 2 * curr;
                }

                // Add this group's score to its parent
                st.push(st.pop() + curr);
            }
        }

        return st.pop();
    }
}