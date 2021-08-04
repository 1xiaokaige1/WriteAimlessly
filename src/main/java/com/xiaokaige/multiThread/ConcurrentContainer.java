package com.xiaokaige.multiThread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author zengkai
 * @date 2021/7/29 16:53
 */
public class ConcurrentContainer {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        boolean flag = queue.offer("abc");
        String take = queue.take();
        if (flag) {
            System.out.println(take);
        }
        SynchronousQueue<String> synQueue = new SynchronousQueue<>();
        Thread thread = new Thread();

    }
}
