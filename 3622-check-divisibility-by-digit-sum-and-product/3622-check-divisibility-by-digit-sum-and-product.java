class Solution {
    public boolean checkDivisibility(int n) {
        int temp = n;
        int sum = 0;
        int prod = 1;
        while (temp > 0) {
            int rem = temp%10;

            sum += rem;
            prod *= rem;
            
            temp /= 10;
        }

        return n%(sum+prod)==0;
    }
}