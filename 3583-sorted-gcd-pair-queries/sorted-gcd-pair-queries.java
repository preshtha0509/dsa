class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        int[] countMultiple = new int[max + 1];
        for (int g = 1; g <= max; g++) {
            for (int m = g; m <= max; m += g) {
                countMultiple[g] += freq[m];
            }
        }

        long[] exact = new long[max + 1];
        for (int g = max; g >= 1; g--) {
            long c = countMultiple[g];
            long pairs = c * (c - 1) / 2;
            for (int m = g + g; m <= max; m += g) {
                pairs -= exact[m];
            }
            exact[g] = pairs;
        }

        long[] prefix = new long[max + 1];
        for (int g = 1; g <= max; g++) {
            prefix[g] = prefix[g - 1] + exact[g];
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i] + 1; // 1-based position
            int lo = 1, hi = max;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (prefix[mid] >= target) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            ans[i] = lo;
        }

        return ans;
    }
}