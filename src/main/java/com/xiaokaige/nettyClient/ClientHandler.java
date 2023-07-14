package com.xiaokaige.nettyClient;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientHandler extends SimpleChannelInboundHandler<PkgDataBean> {

    public static final Logger log = LoggerFactory.getLogger(ClientHandler.class);

    private static final String TAG = "ClientHandler";

    /**
     * 当收到数据的回调
     *
     * @param channelHandlerContext 封装的连接对像
     * @param o
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, PkgDataBean o) throws Exception {
        System.out.println("o = " + o);
    }

    /**
     * 与服务端连接成功的回调
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("与服务端连接成功：{}" + ctx.toString());
    }

    /**
     * 与服务端断开的回调
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        log.info("与服务端断开连接：{}" + ctx.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        Channel channel = ctx.channel();
        if(channel.isActive())ctx.close();
    }
}
