
class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        Map<Integer, int[][]> map = new HashMap<>();
        map.put(1, new int[][]{{0,-1},{0,1}});
        map.put(2, new int[][]{{-1,0},{1,0}});
        map.put(3, new int[][]{{0,-1},{1,0}});
        map.put(4, new int[][]{{0,1},{1,0}});
        map.put(5, new int[][]{{0,-1},{-1,0}});
        map.put(6, new int[][]{{0,1},{-1,0}});
        
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        
        q.offer(new int[]{0,0});
        visited[0][0] = true;
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1];
            
            if (r == m-1 && c == n-1) return true;
            
            int type = grid[r][c];
            
            for (int[] d : map.get(type)) {
                int nr = r + d[0];
                int nc = c + d[1];
                
                if (nr < 0 || nc < 0 || nr >= m || nc >= n || visited[nr][nc])
                    continue;
                
                int nextType = grid[nr][nc];
                
                for (int[] back : map.get(nextType)) {
                    if (nr + back[0] == r && nc + back[1] == c) {
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                        break;
                    }
                }
            }
        }
        
        return false;
    }
}