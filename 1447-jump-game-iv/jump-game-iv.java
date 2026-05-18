class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        
        if (n == 1) return 0;

        Map<Integer, List<Integer>> map = new HashMap<>();

        // Store indices for each value
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        queue.offer(0);
        visited[0] = true;

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int s = 0; s < size; s++) {
                int curr = queue.poll();

                // Reached last index
                if (curr == n - 1) return steps;

                // Move to i - 1
                if (curr - 1 >= 0 && !visited[curr - 1]) {
                    visited[curr - 1] = true;
                    queue.offer(curr - 1);
                }

                // Move to i + 1
                if (curr + 1 < n && !visited[curr + 1]) {
                    visited[curr + 1] = true;
                    queue.offer(curr + 1);
                }

                // Move to same value indices
                if (map.containsKey(arr[curr])) {
                    for (int next : map.get(arr[curr])) {
                        if (!visited[next]) {
                            visited[next] = true;
                            queue.offer(next);
                        }
                    }

                    // Clear to avoid repeated processing
                    map.remove(arr[curr]);
                }
            }

            steps++;
        }

        return -1;
    }
}