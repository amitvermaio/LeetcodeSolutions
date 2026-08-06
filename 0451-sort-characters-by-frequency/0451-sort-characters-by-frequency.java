class Solution {
    public String frequencySort(String s) {
        HashMap<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) 
            freq.put(c, freq.getOrDefault(c, 0)+1);
        
        Map<Integer, LinkedList<Character>> mp = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<Character, Integer> e : freq.entrySet()) {
            char key = e.getKey();
            int val = e.getValue();

            LinkedList<Character> ll = mp.getOrDefault(val, new LinkedList<>());
            ll.addLast(key);
            mp.put(val, ll);
        }

        StringBuilder sb = new StringBuilder();
        for (int val : mp.keySet()) {
            for (char c : mp.get(val)) {
                for (int i=0; i<val; i++) {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }
}