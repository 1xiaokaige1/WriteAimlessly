package com.xiaokaige.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: zk
 * Date: 2022/2/28
 * Time: 9:42
 */
public class LifeCycleWebServer {
    private final ExecutorService exec = Executors.newFixedThreadPool(10);

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(80);

        while(!exec.isShutdown()){
            try{
                Socket conn = server.accept();
            }catch (Exception e){
                System.out.println("task was be rejected");
            }
        }

    }
}
