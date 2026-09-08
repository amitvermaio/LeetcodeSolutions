class Solution {
    public long countCommas(long n) {
        long result = 0;

        long start = 1000;

        while (start <= n) {
            result += (n - start + 1);
            start *= 1000;
        }        

        return result;
    }
}