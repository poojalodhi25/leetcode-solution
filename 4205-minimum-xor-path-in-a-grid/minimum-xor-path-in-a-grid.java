
class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int max = 0;

        for (int[] row : grid) {
            for (int val : row) {
                max = Math.max(max, val);
            }
        }

        int size = 1;
        while (size <= max) {
            size <<= 1;
        }

        boolean[][][] dp = new boolean[m][n][size];

        dp[0][0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                for (int x = 0; x < size; x++) {

                    if (!dp[i][j][x]) {
                        continue;
                    }

                    if (i + 1 < m) {
                        int next = x ^ grid[i + 1][j];
                        dp[i + 1][j][next] = true;
                    }

                    if (j + 1 < n) {
                        int next = x ^ grid[i][j + 1];
                        dp[i][j + 1][next] = true;
                    }
                }
            }
        }

        for (int x = 0; x < size; x++) {
            if (dp[m - 1][n - 1][x]) {
                return x;
            }
        }

        return -1;
    }
}
