class Solution {
public:
    int reverse(int n) {
        int revNum = 0;

        while (n != 0) {
            int dig = n % 10;
            
            // Niche Wale Step me 10 se multiply kar rhe hain isliye
            // pehle check kar rhe hain ki agar hamar number currently 
            // INT_MAX/10 se bada hai to range se bahar chala jayega
            // to 0 return kar denge and same for INT_MIN/10
            if (revNum > INT_MAX/10 || revNum < INT_MIN/10) {
                return 0;
            }

            revNum = revNum * 10 + dig;
            n = n / 10;
        }

        return revNum;
    }
};