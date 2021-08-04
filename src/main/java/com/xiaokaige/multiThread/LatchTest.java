package com.xiaokaige.multiThread;

import java.util.concurrent.CountDownLatch;

/**
 * @author zengkai
 * @date 2021/7/30 15:02
 */
public class LatchTest {

    public static void main(String[] args) throws InterruptedException {
        long time = timeTasks(9, () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("abc");
            }
        });
        System.out.println(time);
    }


    public static long timeTasks(int nThreads, Runnable task) throws InterruptedException {
        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread(() -> {
                try {
                    startGate.await();
                    task.run();
                } catch (InterruptedException e) {

                } finally {
                    endGate.countDown();
                }
            });
            t.start();
        }
        long start = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long end = System.currentTimeMillis();
        return end - start;
    }
}
