class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;

        double[][] workers = new double[n][2];

        for (int i = 0; i < n; i++) {
            workers[i][0] = (double) wage[i] / quality[i]; // ratio
            workers[i][1] = quality[i];
        }

        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        long qualitySum = 0;
        double ans = Double.MAX_VALUE;

        for (double[] worker : workers) {
            int q = (int) worker[1];
            double ratio = worker[0];

            qualitySum += q;
            maxHeap.offer(q);

            if (maxHeap.size() > k) {
                qualitySum -= maxHeap.poll();
            }

            if (maxHeap.size() == k) {
                ans = Math.min(ans, ratio * qualitySum);
            }
        }

        return ans;
    }
}