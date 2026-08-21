class Solution {

    private long gcd(long a, long b) {
        if (b == 0)
            return a;

        return gcd(b, a % b);
    }

    private boolean check(long value, int[] coins, int n, int k) {
        long count = 0;

        for (int size = 1; size <= n; size++) {
            int mask = (1 << size) - 1;

            while (mask < (1 << n)) {
                long lcm = 1;

                for (int i = 0; i < n; i++) {
                    if (((mask >> i) & 1) != 0) {
                        lcm = (lcm / gcd(lcm, coins[i])) * coins[i];
                    }
                }

                if ((size & 1) == 1)
                    count += value / lcm;
                else
                    count -= value / lcm;

                int smallestBit = mask & -mask;
                int next = mask + smallestBit;

                mask = (((next ^ mask) >> 2) / smallestBit) | next;
            }
        }

        return count >= k;
    }

    public long findKthSmallest(int[] coins, int k) {
        Arrays.sort(coins);

        int[] filtered = new int[coins.length];
        int size = 0;

        for (int coin : coins) {
            boolean valid = true;

            for (int i = 0; i < size; i++) {
                if (coin % filtered[i] == 0) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                filtered[size] = coin;
                size++;
            }
        }

        long left = k;
        long right = (long) filtered[0] * k;

        while (left < right) {
            long mid = left + (right - left) / 2;

            if (check(mid, filtered, size, k))
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }
}