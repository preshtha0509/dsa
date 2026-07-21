class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int active = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') active++;
        }

        String t = "1" + s + "1";

        int m = t.length();
        char[] runChar = new char[m];
        int[] runLen = new int[m];
        int cnt = 0;

        int i = 0;
        while (i < m) {
            char ch = t.charAt(i);
            int j = i;
            while (j < m && t.charAt(j) == ch) j++;

            runChar[cnt] = ch;
            runLen[cnt] = j - i;
            cnt++;

            i = j;
        }

        int ans = active;

        for (int k = 1; k < cnt - 1; k++) {
            if (runChar[k] == '1'
                    && runChar[k - 1] == '0'
                    && runChar[k + 1] == '0') {

                int gain = runLen[k - 1] + runLen[k + 1];
                ans = Math.max(ans, active + gain);
            }
        }

        return ans;
    }
}