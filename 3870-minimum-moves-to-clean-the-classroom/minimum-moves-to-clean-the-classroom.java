class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startX = -1, startY = -1;
        List<int[]> litterPositions = new ArrayList<>();
     
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'L') {
                    litterPositions.add(new int[]{i, j, litterPositions.size()});
                }
            }
        }
        
        int numLitters = litterPositions.size();
        int fullMask = (1 << numLitters) - 1;
    
        int[][][] bestEnergy = new int[m][n][1 << numLitters];
        for (int[][] row : bestEnergy) {
            for (int[] cell : row) {
                Arrays.fill(cell, -1);
            }
        }
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY, 0, energy, 0});
        bestEnergy[startX][startY][0] = energy;
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int mask = curr[2];
            int e = curr[3];
            int steps = curr[4];
            
            if (mask == fullMask) {
                return steps;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || classroom[nx].charAt(ny) == 'X') {
                    continue;
                }
                
                char cell = classroom[nx].charAt(ny);
                int newE = e - 1;
                int newMask = mask;
                
                if (newE < 0) {
                    continue;
                }
                
                if (cell == 'L') {
                    for (int k = 0; k < litterPositions.size(); k++) {
                        if (litterPositions.get(k)[0] == nx && litterPositions.get(k)[1] == ny) {
                            newMask |= (1 << k);
                            break;
                        }
                    }
                }
                
                if (cell == 'R') {
                    newE = energy;
                }
    
                if (newE <= bestEnergy[nx][ny][newMask]) {
                    continue;
                }
                
                bestEnergy[nx][ny][newMask] = newE;
                queue.offer(new int[]{nx, ny, newMask, newE, steps + 1});
            }
        }
        
        return -1; 
    }
}