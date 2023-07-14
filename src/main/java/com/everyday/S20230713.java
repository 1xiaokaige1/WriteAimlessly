package com.everyday;

import java.util.Arrays;

/**
 * @author: zk
 * Date: 2023/7/13
 * Time: 9:50
 */
public class S20230713 {

    public static void main(String[] args) {
        int[][] matrix = {{-84, -36, 2}, {87, -79, 10}, {42, 10, 63}};
        int sum = minFallingPathSum(matrix);
        int sum2 = minFallingPathSum2(matrix);
        System.out.println("sum = " + sum);
        System.out.println("sum2 = " + sum2);
    }

    public static int minFallingPathSum(int[][] matrix) {
        int len = matrix.length;
        int[][] result = new int[len][len];
        System.arraycopy(matrix[0], 0, result[0], 0, len);
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int tmp = result[i - 1][j];
                if (j > 0) {
                    tmp = Math.min(result[i - 1][j - 1], tmp);
                }
                if (j < len - 1) {
                    tmp = Math.min(result[i - 1][j + 1], tmp);
                }
                result[i][j] = matrix[i][j] + tmp;
            }
        }
        return Arrays.stream(result[len - 1]).min().getAsInt();
    }


    public static int minFallingPathSum2(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        System.arraycopy(matrix[0], 0, dp[0], 0, n);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int mn = dp[i - 1][j];
                if (j > 0) {
                    mn = Math.min(mn, dp[i - 1][j - 1]);
                }
                if (j < n - 1) {
                    mn = Math.min(mn, dp[i - 1][j + 1]);
                }
                dp[i][j] = mn + matrix[i][j];
            }
        }
        return Arrays.stream(dp[n - 1]).min().getAsInt();
    }

}
