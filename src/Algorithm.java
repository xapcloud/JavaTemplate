package src;

public class Algorithm {
    void nextPermutation(int[] nums) {
        int maxIdx = nums.length - 1;
        int i = maxIdx;
        while (i > 0 && nums[i] <= nums[i - 1]) {
            i--;
        }
        if (i > 0) {
            for (int j = maxIdx; j >= i; j--) {
                if (nums[j] > nums[i - 1]) {
                    int temp = nums[i - 1];
                    nums[i - 1] = nums[j];
                    nums[j] = temp;
                    break;
                }
            }
        }
        int midIdx = i + (maxIdx - i) / 2;
        for (int k = i; k <= midIdx; k++) {
            int temp = nums[k];
            nums[k] = nums[i + maxIdx - k];
            nums[i + maxIdx - k] = temp;
        }
    }
}
