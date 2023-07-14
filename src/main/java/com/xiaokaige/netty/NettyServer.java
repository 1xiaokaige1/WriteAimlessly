/*
package com.xiaokaige.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

*/
/**
 * 暂时不修改了 netty和springboot端口冲突 无法解决
 *
 * @author ly
 * @date 2022/4/25
 *//*


@Component
@Order(0)
public class NettyServer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(NettyServer.class);

    @Resource
    private RequestAuthInterceptor interceptor;
    @Resource
    private NettyProperties properties;

    @Override
    // @Async("specialTaskExecutor")
    public void run(String... args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        EventLoopGroup bossGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("boss"));
        EventLoopGroup workerGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("worker"));

        try {
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.TRACE))
                    .childHandler(new WebsocketServerInitializer(interceptor, properties));

            ChannelFuture future = serverBootstrap.bind(properties.getPort()).sync();
            future.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    workerGroup.shutdownGracefully();
                    bossGroup.shutdownGracefully();
                    log.info(future.channel().toString() + "链路关闭");
                }
            });
        } catch (Exception ex) {
            log.error("netty启动发生异常 => ", ex);
        }
    }
}
*/
