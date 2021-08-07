package com.xiaokaige.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author zengkai
 * @date 2021/8/4 18:58
 */
public class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        TimeUnit.SECONDS.sleep(3);
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}
