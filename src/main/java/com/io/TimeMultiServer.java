package com.io;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: zk
 * Date: 2022/11/1
 * Time: 18:55
 */
@Slf4j
public class TimeMultiServer {
    public static void main(String[] args) {
        Integer port = 9091;
        MultiServer multiServer = new MultiServer(port);
        new Thread(multiServer, "selfDefine-thread").start();

    }

    static class MultiServer implements Runnable {

        private Selector selector;

        private ServerSocketChannel ssc;

        private volatile boolean stop;

        public MultiServer(Integer port) {
            try {
                selector = Selector.open();
                ssc = ServerSocketChannel.open();
                ssc.configureBlocking(false);
                ssc.socket().bind(new InetSocketAddress(port), 1024);
                ssc.register(selector, SelectionKey.OP_ACCEPT);
                log.info("Server is start in port :" + port);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        public void stop() {
            stop = true;
        }

        @Override
        public void run() {
            while (!stop) {
                try {
                    selector.select(1000);
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> it = keys.iterator();
                    SelectionKey key = null;
                    while (it.hasNext()) {
                        key = it.next();
                        it.remove();
                        try{
                            handleInput(key);
                        }catch (Exception e){
                            if(key != null){
                                key.cancel();
                                if(key.channel() != null){
                                    key.channel().close();
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if(selector != null){
                try{
                    selector.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        private void handleInput(SelectionKey key) throws IOException {
            if(key.isValid()){
                if(key.isAcceptable()){
                   ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                }

                if(key.isReadable()){
                   SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int readBytes = sc.read(readBuffer);
                    if(readBytes > 0){
                        readBuffer.flip();
                        byte[] bytes = new byte[readBuffer.remaining()];
                        readBuffer.get(bytes);
                        String message = new String(bytes, StandardCharsets.UTF_8);
                        log.info("server receive message: {}", message);
                        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(message) ?
                                new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                        doWrite(sc, currentTime);
                    }else if (readBytes < 0){
                        key.cancel();
                        sc.close();
                    }else{

                    }
                }
            }
        }

        private void doWrite(SocketChannel sc, String resp) throws IOException {
            byte[] bytes = resp.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            sc.write(writeBuffer);
        }
    }
}
