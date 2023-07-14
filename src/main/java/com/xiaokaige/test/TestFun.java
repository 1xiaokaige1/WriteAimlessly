package com.xiaokaige.test;

import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * @author: zk
 * Date: 2022/8/23
 * Time: 12:25
 */
public class TestFun {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        System.out.println(time + 60*5*1000);
        String s = DigestUtils.md5DigestAsHex("12345678w".getBytes());
        System.out.println("s = " + s.toUpperCase());
    }


}
