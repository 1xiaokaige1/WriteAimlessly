package com.xiaokaige.test;

import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: zk
 * Date: 2023/4/20
 * Time: 10:35
 */
public class ParameterTest {


    public static void main(String[] args) {
        test();
    }

    public static void test() {
        /*List<String> messageObjectList = new ArrayList<>();
        for (int i = 0; i < 1001; i++) {
            messageObjectList.add(i + "");
        }
        int batchCnt = messageObjectList.size() % 1000 == 0 ? messageObjectList.size() / 1000 : messageObjectList.size() / 1000 + 1;
        System.out.println("batchCnt = " + batchCnt);
        List<String> tmpMessageObjectList = new ArrayList<>();
        for (int i = 0; i < batchCnt; i++) {
            tmpMessageObjectList = messageObjectList.subList(i * 1000, i + 1 == batchCnt ? messageObjectList.size() : (i + 1) * 1000);
        }
        System.out.println("tmpMessageObjectList = " + tmpMessageObjectList.size());*/
        /*Date date = new Date();
        System.out.println("date = " + date);

        String s = DigestUtils.md5DigestAsHex(("1000065" + "2" + new Date()).getBytes());
        System.out.println("s = " + s);*/

        BigDecimal add = new BigDecimal(10.54)
                .multiply(new BigDecimal(2.3).divide(new BigDecimal(100), 5, RoundingMode.HALF_UP))
                .add(new BigDecimal(0.3));
        System.out.println("add = " + add);


        BigDecimal subtract = new BigDecimal(10.54).subtract(new BigDecimal(10.54)
                .multiply(new BigDecimal(2.3).divide(new BigDecimal(100), 5, RoundingMode.HALF_UP))
                .add(new BigDecimal(0.3)));
        System.out.println("subtract = " + subtract);
    }

}



