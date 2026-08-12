function maxSubarrayLength(nums: number[], k: number): number {
    const n = nums.length;
    let i = 0, j = 0;
    let maxlen = 1;

    const mp = new Map<number, number>();

    while (j < n) {
        // Add nums[j] to the window
        mp.set(nums[j], (mp.get(nums[j]) ?? 0) + 1);

        // If frequency exceeds k, shrink from left
        while (i < j && mp.get(nums[j])! > k) {
            mp.set(nums[i], mp.get(nums[i])! - 1);
            i++;
        }

        // Update maximum window length
        maxlen = Math.max(maxlen, j - i + 1);

        j++;
    }

    return maxlen;
}