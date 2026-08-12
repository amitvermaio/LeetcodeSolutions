class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int i=0, j=0;
        int maxlen = 0;
        Map<Character, Integer> mp = new HashMap<>();
        while (j < n) {
            mp.put(s.charAt(j), mp.getOrDefault(s.charAt(j), 0) + 1);

            while (i<j && mp.get(s.charAt(j)) > 1) {
                mp.put(s.charAt(i), mp.get(s.charAt(i))-1);
                i++;
            }

            maxlen = Math.max(maxlen, j-i+1);
            j++;
        }

        return maxlen;
    }
}