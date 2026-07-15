class Solution {
    public boolean isHappy(int n) {
        boolean[] visited = new boolean[811];

        while (n != 1) {
            n = getNext(n);

            if (visited[n])
                return false;

            visited[n] = true;
        }

        return true;
    }

    private int getNext(int n) {
        int sum = 0;

        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }

        return sum;
    }
}