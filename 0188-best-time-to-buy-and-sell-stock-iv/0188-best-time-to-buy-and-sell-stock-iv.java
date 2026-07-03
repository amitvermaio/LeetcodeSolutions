class Solution {
    Integer[][][] dp;

    private int mx(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    private int solve(int[] prices, int day, int n, int buy, int cap) {
        if (day >= n) {
            return 0;
        }   

        if (cap <= 0) {
            return 0;
        }

        if (dp[day][buy][cap] != null) {
            return dp[day][buy][cap];
        }

        int profit = 0;
        if (buy == 1) {
            int bought = solve(prices, day+1, n, 0, cap) - prices[day];
            int notBought = solve(prices, day+1, n, 1, cap);
            profit = mx(profit, bought, notBought);
        } else {
            int sell = prices[day] + solve(prices, day+1, n, 1, cap-1);
            int notSell = solve(prices, day+1, n, 0, cap);
            profit = mx(profit, sell, notSell);
        }

        return dp[day][buy][cap] = profit;
    }

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int buy = 1;
        dp = new Integer[n+1][buy+1][k+1];
        return solve(prices, 0, n, buy, k);    
    }
}