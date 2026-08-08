/*  **Idea of This Question**
    If I use one character modification at index = i,
    how do I know if the remaining suffix of word2 will be
    found in remaining suffix of word1???
 */

class Solution {
    public int[] validSequence(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[] rightHandSideMatch = new int[m];

        int rightMatched = 0;
        int i=m-1, j=n-1;
        while (i >= 0) {
            if (j>=0 && word1.charAt(i)==word2.charAt(j)) {
                rightMatched += 1;
                j--;
            }

            rightHandSideMatch[i] = rightMatched;
            i--;
        }

        i = 0; j = 0;
        int[] seq = new int[n];
        boolean changePower = true;
        while (i<m && j<n) {
            if (word1.charAt(i) == word2.charAt(j)) {
                seq[j] = i;
                j++;
            } else if (changePower && i+1<m && rightHandSideMatch[i+1]>=n-j-1) {
                seq[j] = i;
                j++;
                changePower = false;
            }
            i++;
        }

        if (j != n)
            return new int[0];
            
        return seq;
    }
}