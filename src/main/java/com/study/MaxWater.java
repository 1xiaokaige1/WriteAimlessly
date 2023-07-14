package com.study;

/**
 * @author: zk
 * Date: 2023/6/27
 * Time: 10:57
 */
public class MaxWater {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        //int[] arr = new int[]{1, 1};
        int maxArea = maxArea(arr);
        System.out.println("maxArea = " + maxArea);
    }

    public static int maxArea(int[] height) {
        int maxWater = 0;
        int len = height.length;
        int effectiveHeight = height[len - 1];
        int effectiveHeightRight = height[0];
        for (int i = 0; i < len - 1; i++) {
            if (i > 0 && height[i] <= effectiveHeightRight) {
                continue;
            }
            for (int j = len - 1; j > i; j--) {
                if (j < len - i - 1 && height[j] <= effectiveHeight) {
                    continue;
                }
                int tmpWater = height[i] >= height[j] ? height[j] * (j - i) : height[i] * (j - i);
                if (tmpWater > maxWater) {
                    maxWater = tmpWater;
                    effectiveHeight = height[j];
                    effectiveHeightRight = height[i];
                }
            }
        }
        return maxWater;
    }

    /*public static int maxAreaTwo(int[] height) {
        int len = height.length;
        int left = 0;
        int right = len - 1;

    }*/
}
