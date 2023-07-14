package com.io;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author: zk
 * Date: 2022/10/31
 * Time: 9:04
 */
@Slf4j
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 9090;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("服务端启动成功。。。");
            while (true) {
                Socket socket = serverSocket.accept();
                log.info("客户端建立连接。。。");
                new Thread(new TimeClient(socket)).start();
            }
        }
    }


    static class TimeClient implements Runnable {

        private final Socket socket;

        public TimeClient(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader in = null;
            PrintWriter out = null;
            try {
                in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                out = new PrintWriter(this.socket.getOutputStream(), true);
                String body;
                String currentTime;
                while (true) {
                    body = in.readLine();
                    if (null == body) {
                        break;
                    }
                    log.info("服务器收到消息:{}", body);
                    currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    out.println(currentTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    out.close();
                }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
