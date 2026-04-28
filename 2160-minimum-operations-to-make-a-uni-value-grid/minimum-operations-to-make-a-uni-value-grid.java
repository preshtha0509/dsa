class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length;
        int[] arr = new int[m * n];

        int idx = 0;
        for (int[] row : grid) {
            for (int val : row) {
                arr[idx++] = val;
            }
        }

        int remainder = arr[0] % x;
        for (int val : arr) {
            if (val % x != remainder) {
                return -1;
            }
        }

        Arrays.sort(arr);

        int median = arr[arr.length / 2];

        int operations = 0;
        for (int val : arr) {
            operations += Math.abs(val - median) / x;
        }
        
        return operations;
    }
}