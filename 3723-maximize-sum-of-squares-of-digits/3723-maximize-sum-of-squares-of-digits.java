class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num) return "";

        StringBuilder sb = new StringBuilder(num);
        int remaining = sum;

        for (int i = 0; i < num; i++) {
            int d = Math.min(9, remaining);
            sb.append(d);
            remaining -= d;
        }

        return sb.toString();
    }
}