class Solution {
    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        HashMap<Character, Character> mp = new HashMap<>();
        Set<Character> vis = new HashSet<>();
        // ek char ek hi char se map ho do se na ho
        for (int i=0; i<n; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            char mappedChar = mp.getOrDefault(c1, Character.MAX_VALUE);
            boolean visC2 = vis.contains(c2);

            if (
                (mappedChar!=Character.MAX_VALUE && mappedChar!=c2) ||
                (visC2 && !mp.containsKey(c1))
            ) {
                return false;
            }

            mp.put(c1, c2);
            vis.add(c2);
        }

        return true;
    }
}