class Solution {
    public String reverseWords(String s) {
        String[] strs = s.trim().split("\\s+");
        int n = strs.length;
        int i = 0, j = n-1;
        while (i < j) {
            String temp = strs[i];
            strs[i] = strs[j];
            strs[j] = temp;
            i++; j--;
        }

        StringBuilder sb = new StringBuilder();
        for (i=0; i<n; i++) {
            if (i > 0)
                sb.append(" ");
            sb.append(strs[i]);
        }

        return sb.toString();
    }
}