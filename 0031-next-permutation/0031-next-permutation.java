class Solution {
    static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++; j--;
        }
    }

    public void nextPermutation(int[] nums) {
        int pivot = -1, n = nums.length;
        for (int i=n-1; i>=1; i--) {
            if (nums[i-1] < nums[i]) {
                pivot = i-1;
                break;
            }
        }

        if (pivot == -1) {
            reverse(nums, 0, n-1);
            return;
        }

        // 2nd step: just next greater element
        for (int i=n-1; i>pivot; i--) {
            if (nums[i] > nums[pivot]) {
                int temp = nums[i];
                nums[i] = nums[pivot];
                nums[pivot] = temp;
                break;
            }
        }

        // 3rd step: reverse(pivot+1, n-1)
        reverse(nums, pivot+1, n-1);
    }
}