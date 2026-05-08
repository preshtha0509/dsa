class Solution {
    private static final int MAX = 1_000_000;
    private static final int[] spf = new int[MAX + 1];

    static {
        for (int i = 0; i <= MAX; i++) spf[i] = i;
        for (int i = 2; (long) i * i <= MAX; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= MAX; j += i) {
                    if (spf[j] == j) spf[j] = i;
                }
            }
        }
    }

    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

      
        Map<Integer, List<Integer>> primeToIndices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (x > 1) {
                int p = spf[x];
                primeToIndices.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
                while (x % p == 0) x /= p;
            }
        }

      
        boolean[] visited = new boolean[n];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        visited[0] = true;

        for (int steps = 0; !q.isEmpty(); steps++) {
            int size = q.size();
            while (size-- > 0) {
                int i = q.poll();
                if (i == n - 1) return steps;

               
                if (i > 0 && !visited[i - 1]) {
                    visited[i - 1] = true;
                    q.offer(i - 1);
                }
                if (i < n - 1 && !visited[i + 1]) {
                    visited[i + 1] = true;
                    q.offer(i + 1);
                }

              
                int val = nums[i];
                if (val > 1 && spf[val] == val) {
                    
                    List<Integer> targets = primeToIndices.remove(val);
                    if (targets != null) {
                        for (int idx : targets) {
                            if (!visited[idx]) {
                                visited[idx] = true;
                                q.offer(idx);
                            }
                        }
                    }
                }
            }
        }

        return -1;
    }
}