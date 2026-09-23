class Solution {
public:
    int countPrimes(int n) {
        vector<bool> isPrime(n, true);

        if (n <= 2)
            return 0;

        isPrime[0] = isPrime[1] = false;

        for (int i = 2; 1LL * i * i < n; i++) {
            if (isPrime[i]) {
                for (long long j = 1LL * i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;

        for (int i = 2; i < n; i++) {
            if (isPrime[i])
                count++;
        }

        return count;
    }
};