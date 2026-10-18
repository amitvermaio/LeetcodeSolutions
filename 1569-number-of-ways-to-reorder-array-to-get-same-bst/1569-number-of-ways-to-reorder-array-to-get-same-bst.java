class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] PT;

    private long solve(List<Integer> nums) {
        int m = nums.size();

        if (m < 3) {
            return 1;
        }

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        int root = nums.get(0);
        for (int i = 1; i < m; i++) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }

        long leftWays = solve(left) % MOD;
        long rightWays = solve(right) % MOD;

        return (((leftWays * rightWays) % MOD) * PT[m - 1][left.size()]) % MOD;
    }

    public int numOfWays(int[] nums) {
        int n = nums.length;

        // Build Pascal's triangle for nCr under modulo
        PT = new long[n + 1][];
        for (int i = 0; i <= n; i++) {
            PT[i] = new long[i + 1];
            Arrays.fill(PT[i], 1);

            for (int j = 1; j < i; j++) {
                PT[i][j] = (PT[i - 1][j - 1] + PT[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int x : nums) {
            list.add(x);
        }

        return (int) ((solve(list) - 1 + MOD) % MOD);
    }
}