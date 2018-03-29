package algorithms.dp;

/**
 * 给定一个矩阵m 起点在左上角 终点在右下角
 * 每次只能向右或向下走 计算所走路径上数字累加和
 * 返回所有路径中最小的路径和
 *
 * @author MelloChan
 * @date 2018/3/29
 */
public class MatrixPathSum {
    public static void main(String[] args) {
        int[][] m = new int[][]{
                {1, 3, 5, 9},
                {8, 1, 3, 4},
                {5, 0, 6, 1},
                {8, 8, 4, 0}
        };
        System.out.println(matrixPathSum1(m));
        System.out.println(matrixPathSum2(m));
    }

    /**
     * 时间空间复杂度都为O(m*n)
     *
     * @param matrix
     * @return
     */
    public static int matrixPathSum1(int[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0] == null || matrix[0].length <= 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = matrix[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = matrix[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 优化空间复杂度
     * 使其为O(min{m,n})
     *
     * @param matrix
     * @return
     */
    public static int matrixPathSum2(int[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0] == null || matrix[0].length <= 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int length = Math.min(m, n);
        int[] dp = new int[length];
        dp[0] = matrix[0][0];
        if (m >= n) {
            for (int i = 1; i < length; i++) {
                dp[i] = dp[i - 1] + matrix[0][i];
            }
            for (int i = 1; i < m; i++) {
                dp[0]+=matrix[i][0];
                for (int j = 1; j < n; j++) {
                    dp[j]=Math.min(dp[j],dp[j-1])+matrix[i][j];
                }
            }
        } else {
            for (int i = 1; i < length; i++) {
                dp[i] = dp[i - 1] + matrix[i][0];
            }
            for (int i = 1; i < n; i++) {
                dp[0]+=matrix[0][i];
                for (int j = 1; j < m; j++) {
                    dp[j]=Math.min(dp[j],dp[j-1])+matrix[j][i];
                }
            }
        }
        return dp[length - 1];
    }
}
