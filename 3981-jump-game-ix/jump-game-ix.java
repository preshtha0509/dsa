class Solution {
    public int[] maxValue(int[] nums) {

        int n = nums.length;
        int[] ans = new int[n];

        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        int start = 0;
        int currentMax = nums[0];

        for (int i = 0; i < n - 1; i++) {

            currentMax = Math.max(currentMax, nums[i]);

            if (currentMax <= suffixMin[i + 1]) {

                int segMax = Integer.MIN_VALUE;

                for (int j = start; j <= i; j++) {
                    segMax = Math.max(segMax, nums[j]);
                }

                for (int j = start; j <= i; j++) {
                    ans[j] = segMax;
                }

                start = i + 1;
                currentMax = nums[start];
            }
        }

        int segMax = Integer.MIN_VALUE;

        for (int j = start; j < n; j++) {
            segMax = Math.max(segMax, nums[j]);
        }

        for (int j = start; j < n; j++) {
            ans[j] = segMax;
        }

        return ans;
    }
}