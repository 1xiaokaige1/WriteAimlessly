package com.xiaokaige.controller;

import java.math.BigDecimal;

/**
 * @author: zk
 * Date: 2022/3/4
 * Time: 9:55
 */
public class FuncTest {
    public static void main(String[] args) {
        BigDecimal number = BigDecimal.ZERO;
        BigDecimal result = number.add(new BigDecimal(1));
        System.out.println("result = " + result);
    }
}
