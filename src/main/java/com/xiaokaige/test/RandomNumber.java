package com.xiaokaige.test;

/**
 * @author: zk
 * Date: 2022/9/8
 * Time: 15:58
 */
public class RandomNumber {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String s = generateRegId("123");
            System.out.println("s = " + s);
        }
    }

    private static String generateRegId(String appId) {
        long currentTime = System.currentTimeMillis();
        long randomNumber = (int)((Math.random()*9+1)*1000);
        return appId + currentTime + randomNumber;
    }
}
