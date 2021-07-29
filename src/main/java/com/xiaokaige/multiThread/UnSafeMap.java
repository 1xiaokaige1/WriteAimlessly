package com.xiaokaige.multiThread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zengkai
 * @date 2021/7/24 15:29
 */
public class UnSafeMap {
    public static void main(String[] args) {
            Container container = new Container();
        new Thread(() -> {
            Integer service = container.service(2);
            System.out.println("one: " + service);
        }).start();
        new Thread(() -> {
            Integer service = container.service(2);
            System.out.println("tow: " + service);
        }).start();
    }
}

class Container {
    public final AtomicReference<Integer> lastNumber = new AtomicReference<>();

    public final AtomicReference<Integer> lastFactors = new AtomicReference<>();

    public synchronized Integer service(Integer i) {
        if (i.equals(lastNumber.get())) {
            return lastFactors.get();
        } else {
            lastNumber.set(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lastFactors.set(i);
            return i;
        }
    }
}
