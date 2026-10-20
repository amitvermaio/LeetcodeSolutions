class Solution {
public:

    // Function to compute the LPS (Longest Proper Prefix which is also Suffix) array
    void computeLPS(vector<int>& pattern, vector<int>& lps) {
        int M = pattern.size();
        int len = 0; // Length of the previous longest prefix & suffix
    
        lps[0] = 0; // Because there is no proper suffix and prefix of pattern[0..0]
    
        int i = 1;
        while (i < M) {
            if (pattern[i] == pattern[len]) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; //You can also write, len = len-1;
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }
    
    bool KMPSearch(vector<int>& pat, vector<int>& txt) {
        int N = txt.size();
        int M = pat.size();
        vector<int> result;
        
        // Create an LPS array to store the longest proper prefix which is also a suffix
        //lps[i] = the longest proper prefix of pat[0..i] which is also a suffix of pat[0..i]. 
        vector<int> lps(M, 0);
        computeLPS(pat, lps);
    
        int i = 0; // Index for text
        int j = 0; // Index for pattern
    
        while (i < N) {
            if (pat[j] == txt[i]) {
                i++;
                j++;
            }
    
            if (j == M) {
                return true; //Found a match
            } else if (i < N && pat[j] != txt[i]) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        
        return false;
    }

    bool isRotationPossible(vector<int>& vec1, vector<int>& vec2) {
        vector<int> text = vec2;
        text.insert(end(text), begin(vec2), end(vec2));

        return KMPSearch(vec1, text); //text = vec2+vec2
    }

    int sortableIntegers(vector<int>& nums) {
        int n = nums.size();

        vector<int> sorted = nums;
        sort(begin(sorted), end(sorted));

        int result = 0;

        for(int k = 1; k <= n; k++) {
            if(n%k != 0)
                continue;
            
            bool valid = true;
            for(int start = 0; start < n; start += k) { //check k block sizes
                vector<int> vec1(begin(nums) + start, begin(nums) + start + k);
                vector<int> vec2(begin(sorted) + start, begin(sorted) + start + k);

                if(!isRotationPossible(vec1, vec2)) {
                    valid = false;
                    break;
                }
            }

            if(valid) {
                result += k;
            }
        }

        return result;
    }
};