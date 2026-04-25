
class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] arr = new long[n];

        // Convert to 1D perimeter
        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            if (y == 0) arr[i] = x;
            else if (x == side) arr[i] = side + y;
            else if (y == side) arr[i] = 3L * side - x;
            else arr[i] = 4L * side - y;
        }

        Arrays.sort(arr);

        long[] ext = new long[2 * n];
        for (int i = 0; i < n; i++) {
            ext[i] = arr[i];
            ext[i + n] = arr[i] + 4L * side;
        }

        long left = 0, right = 4L * side, ans = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            if (canPick(ext, n, k, mid, 4L * side)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return (int) ans;
    }

    private boolean canPick(long[] arr, int n, int k, long dist, long perimeter) {
        for (int i = 0; i < n; i++) {
            int count = 1;
            long last = arr[i];
            int idx = i;

            // pick k points greedily
            for (int j = 1; j < k; j++) {
                // binary search next valid point
                int next = lowerBound(arr, idx + 1, i + n, last + dist);
                if (next == -1) break;

                last = arr[next];
                idx = next;
                count++;
            }

            if (count == k) {
                // circular check
                if (arr[i] + perimeter - last >= dist) return true;
            }
        }
        return false;
    }

    private int lowerBound(long[] arr, int l, int r, long target) {
        int ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] >= target) {
                ans = mid;
                r = mid - 1;
            } else l = mid + 1;
        }
        return ans;
    }
}