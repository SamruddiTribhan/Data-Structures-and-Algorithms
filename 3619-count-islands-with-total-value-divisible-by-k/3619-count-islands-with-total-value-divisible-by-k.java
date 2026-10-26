class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long sum = dfs(grid, i, j);

                    if (sum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private long dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        long sum = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int[] stack = new int[m * n];
        int top = 0;

        stack[top++] = i * n + j;

        sum += grid[i][j];
        grid[i][j] = 0;

        while (top > 0) {
            int pos = stack[--top];
            int row = pos / n;
            int col = pos % n;

            for (int d = 0; d < 4; d++) {
                int nr = row + dr[d];
                int nc = col + dc[d];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n
                        && grid[nr][nc] > 0) {

                    sum += grid[nr][nc];
                    grid[nr][nc] = 0;

                    stack[top++] = nr * n + nc;
                }
            }
        }

        return sum;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna