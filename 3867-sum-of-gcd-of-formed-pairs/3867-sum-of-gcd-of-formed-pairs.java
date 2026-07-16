class Solution {
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a%b);
    }
    public long gcdSum(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        int[] prefGcd = new int[n];
        int mx = nums[0];
        prefGcd[0] = nums[0];
        for (int i=1; i<n; i++) {
            mx = Math.max(mx, nums[i]);
            prefGcd[i] = gcd(mx, nums[i]);
        }
        Arrays.sort(prefGcd);
        long ans = 0;
        int i=0, j=n-1;
        while (i < j) {
            ans += gcd(prefGcd[i++], prefGcd[j--]);
        }

        return ans;
    }
}