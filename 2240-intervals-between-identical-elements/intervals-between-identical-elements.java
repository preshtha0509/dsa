
class Solution {
    public long[] getDistances(int[] arr) {
        int n = arr.length;
        long[] result = new long[n];

        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        for (List<Integer> list : map.values()) {
            int size = list.size();
            
            long[] prefix = new long[size];
            prefix[0] = list.get(0);
            
            for (int i = 1; i < size; i++) {
                prefix[i] = prefix[i - 1] + list.get(i);
            }
            
            for (int i = 0; i < size; i++) {
                int idx = list.get(i);
                
                long left = 0;
                if (i > 0) {
                    left = (long)i * idx - prefix[i - 1];
                }
                
                long right = 0;
                if (i < size - 1) {
                    right = (prefix[size - 1] - prefix[i]) 
                          - (long)(size - i - 1) * idx;
                }
                
                result[idx] = left + right;
            }
        }
        
        return result;
    }
}