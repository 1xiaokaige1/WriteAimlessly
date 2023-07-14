package com.io;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: zk
 * Date: 2022/11/2
 * Time: 9:04
 */
@Slf4j
public class TimeMultiClient {
    public static void main(String[] args) {
        ClientHandle clientHandle = new ClientHandle("127.0.0.1", 9020);
        new Thread(clientHandle, "client-thread").start();
    }
    
    static class ClientHandle implements Runnable {

        private String host;

        private Integer port;

        private Selector selector;

        private SocketChannel socketChannel;

        private volatile boolean stop;

        public ClientHandle(String host, Integer port) {
            this.host = StringUtils.isEmpty(host) ? "127.0.0.1" : host;
            this.port = port;
            try {
                selector = Selector.open();
                socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        @Override
        public void run() {
            try {
                doConnect();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }

            while (!stop) {
                try {
                    selector.select(1000);
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> it = selectionKeys.iterator();
                    SelectionKey key;
                    while (it.hasNext()) {
                        key = it.next();
                        it.remove();
                        try {
                            handleInput(key);
                        } catch (Exception e) {
                            if (key != null) {
                                key.cancel();
                                if (key.channel() != null) {
                                    key.channel().close();
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }

        private void handleInput(SelectionKey key) throws IOException {
            if (key.isValid()) {
                SocketChannel sc = (SocketChannel) key.channel();
                if (key.isConnectable()) {
                    if (sc.finishConnect()) {
                        sc.register(selector, SelectionKey.OP_READ);
                        doWrite(sc);
                    } else {
                        System.exit(1);
                    }
                }

                if (key.isReadable()) {
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int readBytes = sc.read(readBuffer);
                    if (readBytes > 0) {
                        readBuffer.flip();
                        byte[] bytes = new byte[readBuffer.remaining()];
                        readBuffer.get(bytes);
                        String body = new String(bytes, StandardCharsets.UTF_8);
                        log.info("now is : {}", body);
                        stop = true;
                    } else if (readBytes < 0) {
                        key.cancel();
                        sc.close();
                    } else {

                    }
                }
            }
        }

        private void doConnect() throws IOException {
            if (socketChannel.connect(new InetSocketAddress(host, port))) {
                socketChannel.register(selector, SelectionKey.OP_READ);
                doWrite(socketChannel);
            } else {
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }
        }

        private void doWrite(SocketChannel sc) throws IOException {
            byte[] bytes = "query time order".getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            sc.write(writeBuffer);
            if (!writeBuffer.hasRemaining()) {
                log.info("send msg success");
            }
        }
    }
}
