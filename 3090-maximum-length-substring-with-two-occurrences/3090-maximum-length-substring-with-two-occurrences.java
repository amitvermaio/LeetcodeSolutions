class Solution {
    public int maximumLengthSubstring(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        int maxlen = 0;
        int i = 0;
        for (int j = 0; j < n; j++) {
            cnt[s.charAt(j) - 'a']++;
            while (cnt[s.charAt(j) - 'a'] > 2) {
                cnt[s.charAt(i) - 'a']--;
                i++;
            }
            maxlen = Math.max(maxlen, j - i + 1);
        }
        return maxlen;
    }
}