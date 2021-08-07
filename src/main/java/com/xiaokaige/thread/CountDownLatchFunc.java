package com.xiaokaige.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author zengkai
 * @date 2021/8/7 15:03
 */
public class CountDownLatchFunc {
    public static void main(String[] args) {
        CountDownLatch startDownLatch = new CountDownLatch(1);
        CountDownLatch endDownLatch = new CountDownLatch(3);

        CountDownLatchRunnable firstRunner = new CountDownLatchRunnable(startDownLatch, endDownLatch, 1000);
        CountDownLatchRunnable secondRunner = new CountDownLatchRunnable(startDownLatch, endDownLatch, 2000);
        CountDownLatchRunnable thirdRunner = new CountDownLatchRunnable(startDownLatch, endDownLatch, 3000);

        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future<Integer> firstFuture = threadPool.submit(firstRunner);
        Future<Integer> secondFuture = threadPool.submit(secondRunner);
        Future<Integer> thirdFuture = threadPool.submit(thirdRunner);

        System.out.println("开始奔跑");
        startDownLatch.countDown();
        try {
            endDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("花费时间: " + (firstFuture.get() + secondFuture.get() + thirdFuture.get()));
        }catch (Exception e){
            e.printStackTrace();
        }

        threadPool.shutdown();
    }
}
