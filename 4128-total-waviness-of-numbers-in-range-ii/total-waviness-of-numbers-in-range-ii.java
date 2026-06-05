class Solution {

    private char[] digits;
    private long[][] memoCnt;
    private long[][] memoSum;
    private boolean[][] seen;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n < 0) return 0;

        digits = Long.toString(n).toCharArray();

        int states = 2 * 3 * 11 * 11; // started * lenState * a * b
        int m = digits.length;

        memoCnt = new long[m][states];
        memoSum = new long[m][states];
        seen = new boolean[m][states];

        long[] res = dfs(0, true, false, 0, 10, 10);
        return res[1];
    }

    private long[] dfs(int pos, boolean tight,
                       boolean started,
                       int lenState, // 0 = no digit, 1 = one digit, 2 = at least two digits
                       int a, int b) {

        if (pos == digits.length) {
            return new long[]{1L, 0L}; // one valid number, no extra waviness
        }

        int state = encode(started, lenState, a, b);

        if (!tight && seen[pos][state]) {
            return new long[]{memoCnt[pos][state], memoSum[pos][state]};
        }

        int limit = tight ? digits[pos] - '0' : 9;

        long totalCnt = 0;
        long totalSum = 0;

        for (int d = 0; d <= limit; d++) {
            boolean nextTight = tight && (d == limit);

            if (!started && d == 0) {
                long[] child = dfs(pos + 1, nextTight, false, 0, 10, 10);
                totalCnt += child[0];
                totalSum += child[1];
                continue;
            }

            if (!started) {
                long[] child = dfs(pos + 1, nextTight, true, 1, 10, d);
                totalCnt += child[0];
                totalSum += child[1];
            } else if (lenState == 1) {
                long[] child = dfs(pos + 1, nextTight, true, 2, b, d);
                totalCnt += child[0];
                totalSum += child[1];
            } else {
                int add = isWave(a, b, d) ? 1 : 0;

                long[] child = dfs(pos + 1, nextTight, true, 2, b, d);

                totalCnt += child[0];
                totalSum += child[1] + child[0] * add;
            }
        }

        if (!tight) {
            seen[pos][state] = true;
            memoCnt[pos][state] = totalCnt;
            memoSum[pos][state] = totalSum;
        }

        return new long[]{totalCnt, totalSum};
    }

    private boolean isWave(int left, int mid, int right) {
        return (mid > left && mid > right) || (mid < left && mid < right);
    }

    private int encode(boolean started, int lenState, int a, int b) {
        int s = started ? 1 : 0;
        return (((s * 3 + lenState) * 11 + a) * 11 + b);
    }
}