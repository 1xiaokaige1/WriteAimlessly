package com.xiaokaige.nettyClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class NettyClient {
    private static final String TAG = "NettyClient";
    private final int PORT = 8082;
    //连接的服务端ip地址
    private final String IP = "192.168.1.56";
    //与服务端的连接通道
    private Channel channel;

    public static final Logger log = LoggerFactory.getLogger(NettyClient.class);

    /**
     *需要在子线程中发起连接
     */
    /*public NettyClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                connect();
            }
        }).start();
    }*/
    /**
     * 连接服务端
     */
    public void connect() {
        try {
            NioEventLoopGroup group = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap()
                    // 指定channel类型
                    .channel(NioSocketChannel.class)
                    // 指定EventLoopGroup
                    .group(group)
                    // 指定Handler
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            //添加发送数据编码器
                            pipeline.addLast(new ClientEncoder());
                            //添加接收数据解码器
                            pipeline.addLast(new ClientDecoder());
                            //添加数据处理器
                            pipeline.addLast(new ClientHandler());
                        }
                    });
            // 连接到服务端
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(IP, PORT));
            //获取连接通道
            channel = channelFuture.sync().channel();
        } catch (Exception e) {
            log.info("连接失败：{}" + e.getMessage());
            e.printStackTrace();
        }
    }

    public Channel getChannel(){
        return channel;
    }
}
