class Solution {
    static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        long[] up = new long[m];
        long[] down = new long[m];

        for (int i = 0; i < m; i++) {
            up[i] = i;           
            down[i] = m - 1 - i;  
        }

        for (int len = 3; len <= n; len++) {
            long[] prefDown = new long[m + 1];
            long[] suffUp = new long[m + 1];

            for (int i = 0; i < m; i++) {
                prefDown[i + 1] = (prefDown[i] + down[i]) % MOD;
            }

            for (int i = m - 1; i >= 0; i--) {
                suffUp[i] = (suffUp[i + 1] + up[i]) % MOD;
            }

            long[] nextUp = new long[m];
            long[] nextDown = new long[m];

            for (int i = 0; i < m; i++) {
                nextUp[i] = prefDown[i];
                nextDown[i] = suffUp[i + 1];
            }

            up = nextUp;
            down = nextDown;
        }

        long ans = 0;
        for (int i = 0; i < m; i++) {
            ans = (ans + up[i] + down[i]) % MOD;
        }

        return (int) ans;
    }
}