class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int count=0;
        int inc = 1;
        for (int i=0; i<n; i++) {
            count += inc;
            if ((i+1)%8==0) inc++;
        }
        return count;
    }
}