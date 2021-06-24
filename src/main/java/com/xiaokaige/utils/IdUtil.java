package com.xiaokaige.utils;

import java.util.UUID;

/**
 * @author zengkai
 * @date 2021/6/24 10:10
 */
public class IdUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }
}
