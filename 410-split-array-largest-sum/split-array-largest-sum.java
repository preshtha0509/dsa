class Solution {
    public int splitArray(int[] nums, int k) {
        long low = 0, high = 0;

        for (int num : nums) {
            low = Math.max(low, num);
            high += num;
        }

        while (low < high) {
            long mid = low + (high - low) / 2;

            if (canSplit(nums, k, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return (int) low;
    }

    private boolean canSplit(int[] nums, int k, long maxSum) {
        int count = 1;
        long currSum = 0;

        for (int num : nums) {
            if (currSum + num > maxSum) {
                count++;
                currSum = num;

                if (count > k) {
                    return false;
                }
            } else {
                currSum += num;
            }
        }

        return true;
    }
}