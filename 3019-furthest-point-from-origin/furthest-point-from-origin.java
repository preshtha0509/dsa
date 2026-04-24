class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int left = 0, right = 0, blank = 0;

        for (char c : moves.toCharArray()) {
            if (c == 'L') left++;
            else if (c == 'R') right++;
            else blank++;
        }

        // Max distance by assigning all '_' optimally
        return Math.abs(right - left) + blank;
    }
}