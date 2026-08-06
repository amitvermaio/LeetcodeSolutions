class Solution {
    private int prod(int n) {
        int prod = 1;
        while (n > 0) {
            prod *= (n%10);
            n /= 10;
        }
        
        return prod;
    }

    public int smallestNumber(int n, int t) {
        while (prod(n)%t != 0) {
            n += 1;
        }

        return n;
    }
}