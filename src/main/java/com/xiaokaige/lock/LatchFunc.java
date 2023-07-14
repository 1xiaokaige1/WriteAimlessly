package com.xiaokaige.lock;

import com.xiaokaige.entity.UserInfoDO;
import io.rong.messages.UserInfo;
import io.rong.methods.user.User;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: zk
 * Date: 2022/2/24
 * Time: 14:52
 */
public class LatchFunc {
    public long computeNThreadsTime(int nThreads, Runnable runnable) throws InterruptedException {
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            try {
                Thread t = new Thread(() -> {
                    try {
                        startLatch.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    runnable.run();
                    endLatch.countDown();
                });
                t.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long startTime = System.currentTimeMillis();
        startLatch.countDown();
        endLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("花费时间为" + (endTime - startTime));
        return endTime - startTime;
    }

    public static void main(String[] args) {
        int count = Runtime.getRuntime().availableProcessors();
        System.out.println("count = " + count);
    }
}
