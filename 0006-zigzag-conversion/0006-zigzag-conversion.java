class Solution {
    public String convert(String s, int numRows) {
        if (numRows==1 || s.length() < numRows) 
            return s;

        ArrayList<Character>[] arr = new ArrayList[numRows];
        for (int i=0; i<numRows; i++)
            arr[i] = new ArrayList<>();
        
        int idx = 0, d = 1;
        for (char c : s.toCharArray()) {
            arr[idx].add(c);
            if (idx == 0)
                d = 1;
            else if (idx == numRows-1)
                d = -1;
            idx += d;
        }

        StringBuilder sb = new StringBuilder();
        for (ArrayList<Character> row : arr) {
            for (char c : row) {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}