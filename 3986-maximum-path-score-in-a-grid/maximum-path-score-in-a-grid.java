class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        // dp[i][j][c] = max score at (i,j) with cost c
        int[][][] dp = new int[m][n][k + 1];

        // initialize with -1 (invalid)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= k; c++) {
                    dp[i][j][c] = -1;
                }
            }
        }

        dp[0][0][0] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int val = grid[i][j];
                int score = val;
                int cost = (val == 0) ? 0 : 1;

                for (int c = 0; c <= k; c++) {

                    if (dp[i][j][c] == -1) continue;

                    // Move DOWN
                    if (i + 1 < m) {
                        int nCost = c + ((grid[i + 1][j] == 0) ? 0 : 1);
                        if (nCost <= k) {
                            int nScore = dp[i][j][c] + grid[i + 1][j];
                            dp[i + 1][j][nCost] = Math.max(dp[i + 1][j][nCost], nScore);
                        }
                    }

                    // Move RIGHT
                    if (j + 1 < n) {
                        int nCost = c + ((grid[i][j + 1] == 0) ? 0 : 1);
                        if (nCost <= k) {
                            int nScore = dp[i][j][c] + grid[i][j + 1];
                            dp[i][j + 1][nCost] = Math.max(dp[i][j + 1][nCost], nScore);
                        }
                    }
                }
            }
        }

        int ans = -1;
        for (int c = 0; c <= k; c++) {
            ans = Math.max(ans, dp[m - 1][n - 1][c]);
        }

        return ans;
    }
}