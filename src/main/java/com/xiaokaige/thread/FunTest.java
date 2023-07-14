package com.xiaokaige.thread;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author: zk
 * Date: 2022/2/28
 * Time: 13:50
 */
public class FunTest {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<BigInteger> blockingQueue = new ArrayBlockingQueue<>(600);
        ProducePrime producePrime = new ProducePrime(blockingQueue);
        new Thread(producePrime).start();
        //Thread.sleep(3000);
        producePrime.cancel();
        Thread.currentThread().join();
    }

}

class ProducePrime extends Thread{
    private final BlockingQueue<BigInteger> queue;

    private volatile boolean flag = false;

    public ProducePrime(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!flag)
                queue.put(p = p.nextProbablePrime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancel() {
        this.flag = true;
        System.out.println("中断线程!");
    }
}
