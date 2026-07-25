class Solution {
    public int maxProduct(int n) {
        int max = -1;
        int smax = -1;
        while (n > 0) {
            int rem = n % 10;
            n /= 10;
            if (rem > max) {
                smax = max;
                max = rem;
            } else if (rem > smax) {
                smax = rem;
            }
        }

        return max * smax;
    }
}