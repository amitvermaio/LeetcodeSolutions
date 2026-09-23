class Solution {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n+1];
        int count = 0;
        for (int i=2; i<n; i++) {
            isPrime[i] = !isPrime[i];
            if (isPrime[i]) {
                count++;
                for (int j=i*2; j<n; j+=i) {
                    isPrime[j] = true;
                }
            }
        }
        return count;
    }
}