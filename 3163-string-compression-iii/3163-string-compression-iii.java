class Solution {
    public String compressedString(String word) {
        int n = word.length();
        int i = 0, j = 0;
        StringBuilder sb = new StringBuilder();
        while (i < n) {
            char curr = word.charAt(i);
            while (j<n && word.charAt(j)==curr) {
                j++;
            }

            int len = j - i;
            while (len > 0) {
                int val = len >= 9 ? 9 : len;
                sb.append(val).append(curr);
                len -= val;
            }

            i = j;
        }   

        return sb.toString();
    }
}