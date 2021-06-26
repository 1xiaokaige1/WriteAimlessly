package com.xiaokaige.thread;

import com.sun.corba.se.pept.transport.ReaderThread;

/**
 * @author zengkai
 * @date 2021/6/26 15:42
 */
public class ReloadSort {

    private static boolean ready;

    private static int number;

    public static void main(String[] args) {
            new ReaderThread().start();
            number = 1;
            ready = false;
    }

    static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
                System.out.println(number);
            }
        }
    }
}
