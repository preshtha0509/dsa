class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] diff = new int[2 * limit + 2];
        
        for (int i = 0; i < n / 2; i++) {
            int a = Math.min(nums[i], nums[n - 1 - i]);
            int b = Math.max(nums[i], nums[n - 1 - i]);
            
            diff[a + 1] -= 1;
            if (b + limit + 1 <= 2 * limit + 1) diff[b + limit + 1] += 1;
            
            diff[a + b] -= 1;
            if (a + b + 1 <= 2 * limit + 1) diff[a + b + 1] += 1;
        }
        
        int result = Integer.MAX_VALUE;
        int current = (n / 2) * 2;
        
        for (int t = 2; t <= 2 * limit; t++) {
            current += diff[t];
            result = Math.min(result, current);
        }
        
        return result;
    }
}