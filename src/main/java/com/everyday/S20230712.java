package com.everyday;

/**
 * @author: zk
 * Date: 2023/7/12
 * Time: 15:50
 */
public class S20230712 {

    public static void main(String[] args) {
        int sum = alternateDigitSum(886996);
        System.out.println("sum = " + sum);
    }


    public static int alternateDigitSum(int n) {
        String s = String.valueOf(n);
        int len = s.length();
        boolean flag = true;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            int tmp = s.charAt(i) - 48;
            if (flag) {
                sum += tmp;
                flag = false;
            } else {
                sum -= tmp;
                flag = true;
            }
        }
        return sum;
    }

    public static int alternateDigitSum2(int n) {
        int res = 0, sign = 1;
        while (n > 0) {
            res += n % 10 * sign;
            sign = -sign;
            n /= 10;
        }
        return -sign * res;
    }


}


