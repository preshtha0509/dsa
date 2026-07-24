class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048; 
        boolean[][] dp = new boolean[4][MAX];
        dp[0][0] = true;

        for (int val : nums) {
            boolean[][] next = new boolean[4][MAX];
            
            for (int c = 0; c <= 3; c++) {
                for (int x = 0; x < MAX; x++) {
                    if (dp[c][x]) next[c][x] = true;
                }
            }

            for (int c = 0; c <= 3; c++) {
                for (int x = 0; x < MAX; x++) {
                    if (!dp[c][x]) continue;

                    for (int t = 1; c + t <= 3; t++) {
                        int contrib = (t % 2 == 0) ? 0 : val;
                        next[c + t][x ^ contrib] = true;
                    }
                }
            }

            dp = next;
        }

        int ans = 0;
        for (int x = 0; x < MAX; x++) {
            if (dp[3][x]) ans++;
        }

        return ans;
    }
}