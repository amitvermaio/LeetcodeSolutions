class Solution {
    public long sumAndMultiply(int n) {
        StringBuilder sb = new StringBuilder(n+"");
        Long nonZeroNum = 0L;
        Long sum = 0L;
        for (char num : sb.toString().toCharArray()) {
            int temp = num - '0';
            if (temp != 0) {
                nonZeroNum *= 10;
                nonZeroNum += temp;
                sum += temp;
            }
        }

        return nonZeroNum * sum;
    }
}