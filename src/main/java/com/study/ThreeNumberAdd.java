package com.study;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

/**
 * @author: zk
 * Date: 2023/6/26
 * Time: 15:13
 */
public class ThreeNumberAdd {
    public static void main(String[] args) throws JsonProcessingException {
        /*int[] nums = new int[]{-1000, -5, -5, -5, -5, -5, -5, -1, -1, -1};
        int target = -14;
        int result = threeSumClosest(nums, target);
        System.out.println("result = " + result);*/
        ObjectMapper objectMapper = new ObjectMapper();
        String abc = objectMapper.readValue("abc", String.class);
        System.out.println("abc = " + abc);
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int length = nums.length;
        Integer gt = null;
        Integer lt = null;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            for (int i1 = i + 1; i1 < nums.length; i1++) {
                for (int j = i1 + 1; j < nums.length; j++) {
                    int tmp = nums[i] + nums[i1] + nums[j];
                    if (tmp >= target) {
                        if (gt == null) {
                            gt = tmp;
                        }
                        if (tmp < gt) {
                            gt = tmp;
                        }
                        break;
                    }
                }
                for (int j = i1 + 1; j < nums.length; j++) {
                    int tmp = nums[length - i - 1] + nums[length - i1 - 1] + nums[length - j - 1];
                    if (tmp <= target) {
                        if (lt == null) {
                            lt = tmp;
                        }
                        if (tmp > lt) {
                            lt = tmp;
                        }
                        break;
                    }
                }
            }
        }
        return lt == null ? gt : gt == null ? lt : gt - target > target - lt ? lt : gt;
    }
}

