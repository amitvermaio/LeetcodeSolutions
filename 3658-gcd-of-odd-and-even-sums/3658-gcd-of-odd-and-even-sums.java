class Solution {
    public int gcdOfOddEvenSums(int n) {
        int inc = 1;
        int oddSum = 0;
        int evenSum = 0;
        for (int i=0; i<n; i++) {
            oddSum += inc;
            evenSum += inc+1;
            inc += 2;
        }

        return gcd(oddSum, evenSum);
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a%b);
    }
}