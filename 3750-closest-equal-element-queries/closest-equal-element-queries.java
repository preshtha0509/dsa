class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
           int n = nums.length;
        
        // Step 1: Map value -> list of indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        List<Integer> result = new ArrayList<>();
        
        // Step 2: Process each query
        for (int q : queries) {
            int val = nums[q];
            List<Integer> list = map.get(val);
            
            // Only one occurrence
            if (list.size() == 1) {
                result.add(-1);
                continue;
            }
            
            // Binary search to find position of q in list
            int idx = Collections.binarySearch(list, q);
            
            int prev = list.get((idx - 1 + list.size()) % list.size());
            int next = list.get((idx + 1) % list.size());
            
            int dist1 = Math.abs(q - prev);
            dist1 = Math.min(dist1, n - dist1);
            
            int dist2 = Math.abs(q - next);
            dist2 = Math.min(dist2, n - dist2);
            
            result.add(Math.min(dist1, dist2));
        }
        
        return result;
    }
}