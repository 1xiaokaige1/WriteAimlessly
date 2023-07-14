package com.study;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zk
 * Date: 2023/6/28
 * Time: 16:19
 */
public class AllRank {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        List<List<Integer>> permute = permute(arr);
        System.out.println("permute = " + permute);


    }

    /**
     * 获取全排列
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        int len = nums.length;



        return list;
    }

    /**
     * 获取全排列
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        int len = nums.length;
        List<Integer> listOne = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            listOne.add(nums[i]);
        }

        return list;
    }
}
