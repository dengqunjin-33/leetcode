package leetcode.DP;

import java.util.Arrays;

public class LeetCode62To64 {
    //62. 不同路径
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    //62 不同路径 第二种写法
    public static int uniquePaths2(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        dp[0][1] = 1;
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    //63 不同路路径2
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length + 1][obstacleGrid[0].length + 1];
        dp[0][1] = 1;

        for (int i = 1; i < obstacleGrid.length + 1; i++) {
            for (int j = 1; j < obstacleGrid[0].length + 1; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                if (1 == obstacleGrid[i - 1][j - 1]) {
                    dp[i][j] = 0;
                }
            }
        }

        return dp[obstacleGrid.length][obstacleGrid[0].length];
    }

    //64. 最小路径和
    public static int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length + 1][grid[0].length + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (1 == i || 1 == j) {
                    dp[i][j] = grid[i - 1][j - 1] + Math.max(dp[i][j - 1], dp[i - 1][j]);
                } else {
                    dp[i][j] = grid[i - 1][j - 1] + Math.min(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[grid.length][grid[0].length];
    }
}
