class Solution {
    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        int[] pos = new int[n];
        int[] digit = new int[n];
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                pos[cnt] = i;
                digit[cnt++] = d;
            }
        }

        long[] val = new long[cnt + 1];
        long[] pow = new long[cnt + 1];
        int[] sum = new int[cnt + 1];

        pow[0] = 1;
        for (int i = 0; i < cnt; i++) {
            pow[i + 1] = (pow[i] * 10) % MOD;
            val[i + 1] = (val[i] * 10 + digit[i]) % MOD;
            sum[i + 1] = sum[i] + digit[i];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = lowerBound(pos, cnt, queries[i][0]);
            int r = upperBound(pos, cnt, queries[i][1]) - 1;

            if (l > r) {
                ans[i] = 0;
                continue;
            }

            int len = r - l + 1;

            long x = (val[r + 1] - val[l] * pow[len] % MOD + MOD) % MOD;
            long digitSum = sum[r + 1] - sum[l];

            ans[i] = (int) (x * digitSum % MOD);
        }

        return ans;
    }

    private int lowerBound(int[] a, int n, int x) {
        int l = 0, r = n;
        while (l < r) {
            int m = (l + r) / 2;
            if (a[m] >= x) r = m;
            else l = m + 1;
        }
        return l;
    }

    private int upperBound(int[] a, int n, int x) {
        int l = 0, r = n;
        while (l < r) {
            int m = (l + r) / 2;
            if (a[m] > x) r = m;
            else l = m + 1;
        }
        return l;
    }
}