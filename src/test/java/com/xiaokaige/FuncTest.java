package com.xiaokaige;

import com.fasterxml.classmate.GenericType;
import com.xiaokaige.entity.StudentDO;
import com.xiaokaige.utils.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: zk
 * Date: 2022/7/19
 * Time: 17:49
 */
public class FuncTest {

    //public static final Logger log = LoggerFactory.getLogger(FuncTest.class);

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("111");
            }
        });
        t.start();
        Thread.sleep(3000);
        t.interrupt();
    }



        /*List<String> tokens = new ArrayList<>();
        for (int i = 0; i < 1001; i++) {
            tokens.add("token" + i);
        }

        int size = tokens.size();
        int batchCount = size / 1000;
        int modCount = size % 1000;
        for (int i = 0; i <= batchCount; i++) {
            if (i == batchCount && modCount == 0) {
                break;
            }
            List<String> sendTokens = tokens.subList(i * 1000, ((i + 1) * 1000) > size ? (i * 1000) + modCount : (i + 1) * 1000);
            log.info("{}第{}批发送的token信息:{}", "测试", i + 1, sendTokens.size());
        }*/


}
