class Solution {
    public int compress(char[] chars) {
        int n = chars.length;
        int i=0;
        int j=0;
        StringBuilder sb = new StringBuilder();
        while (i < n) {
            char curr = chars[i];
            while (j<n && chars[j] == curr) {
                j++;
            }

            if (j-i == 1) {
                sb.append(curr);
            } else {
                sb.append(curr).append(j-i);
            } 

            i = j;
        }

        i = 0;
        int sz = sb.length();
        while (i < sz) {
            chars[i] = sb.charAt(i);
            i++;
            while (i<sz && Character.isDigit(sb.charAt(i))) {
                chars[i] = sb.charAt(i);
                i++;
            }
        } 

        return sz;
    }
}