class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        HashMap<String, Integer> mp = new HashMap<>();
        int idx = 0;
        for (String str : strs) {
            char[] temp = str.toCharArray();
            Arrays.sort(temp);
            String s = new String(temp);
            if (mp.containsKey(s)) {
                ans.get(mp.get(s)).add(str);
            } else {
                ans.add(new ArrayList<>());
                ans.get(idx).add(str);
                mp.put(s, idx);
                idx++;
            }
        }

        return ans;
    }
}