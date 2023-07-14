package com.xiaokaige.test;

import com.stl.util.STLJsonUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author: zk
 * Date: 2022/6/27
 * Time: 19:48
 */
public class funcTest {
    public static void main(String[] args) throws InterruptedException {
        /*Map<String, String> map = new HashMap<>();
        map.put("a", "2");
        map.put("b", "2");
        map.put("v", "2");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                String attach = getAttach(map);
                System.out.println("attach = " + attach);
            }).start();
        }
        Thread.currentThread().join();*/

        StudentDO studentDO = new StudentDO();

    }

    public static String getAttach(Map<String, String> map) {
        List<I18nAttach> i18nAttachList = new ArrayList<>();
        Set<String> keySet = map.keySet();
        int i = 0;
        for (String key : keySet) {
            String value = map.get(key);
            I18nAttach i18nAttach = new I18nAttach(key, value, i, false);
            i18nAttachList.add(i18nAttach);
            i++;
        }
        return STLJsonUtils.obj2json(i18nAttachList);
    }
}
