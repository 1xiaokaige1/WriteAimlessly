package com.xiaokaige.redis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zk
 * Date: 2022/9/20
 * Time: 10:52
 */
public class FuncTest {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.forEach((k,v)->{
            if("a".equals(k)){
                return;
            }
            System.out.println("k = " + k);
        });

    }
}
