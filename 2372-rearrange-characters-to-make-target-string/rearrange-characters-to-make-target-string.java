class Solution {
    public int rearrangeCharacters(String s, String target) {
        int[] sFreq = new int[26];
        int[] tFreq = new int[26];

        for (char c : s.toCharArray()) {
            sFreq[c - 'a']++;
        }

        for (char c : target.toCharArray()) {
            tFreq[c - 'a']++;
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < 26; i++) {
            if (tFreq[i] > 0) {
                ans = Math.min(ans, sFreq[i] / tFreq[i]);
            }
        }

        return ans;
    }
}