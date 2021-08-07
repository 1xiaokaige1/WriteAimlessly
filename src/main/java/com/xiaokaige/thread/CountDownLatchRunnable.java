package com.xiaokaige.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author zengkai
 * @date 2021/8/7 15:05
 */
public class CountDownLatchRunnable implements Callable<Integer> {

    private final CountDownLatch startLatch;
    private final CountDownLatch endLatch;
    private Integer i;

    CountDownLatchRunnable(CountDownLatch startLatch, CountDownLatch endLatch, Integer i) {
        this.startLatch = startLatch;
        this.endLatch = endLatch;
        this.i = i;
    }

    @Override
    public Integer call() throws Exception {
        startLatch.await();
        doRun(i);
        endLatch.countDown();
        return new Integer(i);
    }

    private void doRun(Integer i) {
        System.out.println("第" + i/1000 + "位选手在奔跑");
        try{
            TimeUnit.SECONDS.sleep(i/1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
