class Solution {
    // FOR UNDERSTANDING CODE SEE THIS -> 
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/?envType=problem-list-v2&envId=dvtcg212

    Integer[][] dp;

    private int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    private int solve(int[] prices, int day, int n, int buy, int fee) {
        if (day >= n) {
            return 0;
        }

        if (dp[day][buy] != null) {
            return dp[day][buy];
        }

        int profit = 0;
        if (buy == 1) {
            int bought = solve(prices, day+1, n, 0, fee) - prices[day] - fee;
            int notBought = solve(prices, day+1, n, 1, fee);
            profit = max(profit, bought, notBought);
        } else {
            int sell = prices[day] + solve(prices, day+1, n, 1, fee);
            int notSell = solve(prices, day+1, n, 0, fee);
            profit = max(sell, profit, notSell); // proft ka bhi max le rhe hain kyuki aisa testcase bhi ho sakte hai -> [9, 5, 4, 2, 1]
            // iska answer -ve me aa sakta hai to uss waqt o return karna hai.
        }

        return dp[day][buy] = profit;
    }

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int buy = 1; 
        dp = new Integer[n+1][2];
        return solve(prices, 0, n, buy, fee);
    }
}