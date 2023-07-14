package com.xiaokaige.vo;

import com.xiaokaige.utils.MD5Utils;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author: zk
 * Date: 2022/4/22
 * Time: 10:22
 */
public class FuncTest {
    public static void main(String[] args) {
        /*UUID uuid = UUID.randomUUID();
        System.out.println("uuid = " + uuid);*/

        String head = MD5Utils.md5("service").substring(0,8);
        System.out.println("head = " + head);
    }

}
