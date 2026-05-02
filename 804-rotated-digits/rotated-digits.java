class Solution {
    public int rotatedDigits(int n) {
        int[] dp = new int[n + 1];
        int count = 0;

        for (int i = 0; i <= n; i++) {

            // single digit
            if (i < 10) {
                if (i == 0 || i == 1 || i == 8) dp[i] = 1;
                else if (i == 2 || i == 5 || i == 6 || i == 9) dp[i] = 2;
                else dp[i] = 0;
            } 
            else {
                int last = i % 10;
                int rest = dp[i / 10];

                // if any part invalid → invalid
                if (rest == 0 || last == 3 || last == 4 || last == 7) {
                    dp[i] = 0;
                } 
                // if any part changes → good
                else if (rest == 2 || last == 2 || last == 5 || last == 6 || last == 9) {
                    dp[i] = 2;
                } 
                // otherwise same
                else {
                    dp[i] = 1;
                }
            }

            if (dp[i] == 2) count++;
        }

        return count;
    }
}