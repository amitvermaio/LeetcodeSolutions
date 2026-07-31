class Freq {
    char key;
    int val;
    public Freq(char key, int val) {
        this.key = key;
        this.val = val;
    }
}

class Solution {
    public int minimumPushes(String word) {
        int[] f = new int[26]; 
        int sz = 0;
        for (char ch : word.toCharArray()) {
            if (f[ch - 'a'] == 0) sz++;
            f[ch - 'a']++;
        }
        Freq[] arr = new Freq[sz];
        int idx = 0;
        for (int i=0; i<26; i++) {
            if (f[i] != 0) {
                char ch = (char) ('a' + i);
                arr[idx++] = new Freq(ch, f[i]);
            }
        }
        Arrays.sort(arr, (a, b) -> Integer.compare(b.val, a.val));
        int ans = 0;
        for (int i=0; i<sz; i++) {
            System.out.println(arr[i].key + " " + arr[i].val);
            int count = arr[i].val;
            int inc = (i+1)/8;
            if ((i+1)%8!=0) inc++;
            ans += (count * inc);
        }
        return ans;
    }
}