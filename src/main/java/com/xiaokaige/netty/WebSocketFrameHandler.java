/*
package com.xiaokaige.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

*/
/**
 * @author ly
 * @date 2022/4/25
 *//*

public class WebSocketFrameHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WebSocketFrameHandler.class);
    public static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final int INIT_DELAY = 2000;
    private static final int PUSH_GAP = 200;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channels.add(ctx.channel());
        ctx.channel().eventLoop().scheduleAtFixedRate(() -> {
            TextWebSocketFrame msg = sendData(ctx.channel());
            if (null == msg) return;
            ctx.channel().writeAndFlush(msg);
        }, INIT_DELAY, PUSH_GAP, TimeUnit.MILLISECONDS);
    }

    private TextWebSocketFrame sendData(Channel channel) {
        SymbolChannelParam param = WebsocketClientStore.getParam(channel);
        if (ObjectUtils.isEmpty(param) || (System.currentTimeMillis() - param.getLastTime() > 32000)) {
            WebsocketMessageModel timeoutMsg = WebsocketMessageModel.of(WebsocketCmdHandler.WebsocketMessageType.PUSH.getMsg(), "time out");
            TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(JsonUtil.toJson(timeoutMsg));
            channel.writeAndFlush(textWebSocketFrame);
            WebsocketClientStore.removeChannel(channel);
            channel.pipeline().remove(this);
            channel.close();
            return null;
        }
        SymbolChannelParam symbolChannelParam = WebsocketClientStore.getParam(channel);
        Set<String> symbolIds = symbolChannelParam.getSymbol();
        if (CollectionUtils.isEmpty(symbolIds)) return null;

        Map<String, ZmqSubscribeClient.SymbolModel.SpreadModel> temp = new HashMap<>(ZmqSubscribeClient.spreadMap);
        Map<String, List<ZmqSubscribeClient.SymbolModel.SpreadModel>> symbolMap = new HashMap<>(symbolIds.size(), 1);
        for (String symbolId : symbolIds) {
            int idx = symbolId.indexOf("-");
            String subscribeId = symbolId.substring(0, idx);
            long modelSymbolId = Long.parseLong(symbolId.substring(idx + 1));
            List<ZmqSubscribeClient.SymbolModel.SpreadModel> spreadModels = symbolMap.computeIfAbsent(subscribeId, v -> new ArrayList<>());
            ZmqSubscribeClient.SymbolModel.SpreadModel spreadModel = temp.get(symbolId);
            if (spreadModel != null) {
                spreadModels.add(spreadModel);
            } else {
                ZmqSubscribeClient.SymbolModel.SpreadModel model = new ZmqSubscribeClient.SymbolModel.SpreadModel();
                model.symbolId = modelSymbolId;
                spreadModels.add(model);
            }
        }

        List<ZmqSubscribeClient.SymbolModel> symbolModels = new ArrayList<>();
        for (Map.Entry<String, List<ZmqSubscribeClient.SymbolModel.SpreadModel>> stringListEntry : symbolMap.entrySet()) {
            long subscribeId = Long.parseLong(stringListEntry.getKey());
            ZmqSubscribeClient.SymbolModel symbolModel = new ZmqSubscribeClient.SymbolModel();
            symbolModel.subscribeId = subscribeId;
            symbolModel.spreads = stringListEntry.getValue();
            symbolModels.add(symbolModel);
        }

        String json = JsonUtil.toJson(symbolModels);
        WebsocketMessageModel frame = WebsocketMessageModel.of(WebsocketCmdHandler.WebsocketMessageType.PUSH.getMsg(), json);
        String resp = JsonUtil.toJson(frame);
        return new TextWebSocketFrame(resp);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SymbolChannelParam param = WebsocketClientStore.removeChannel(ctx.channel());
        if (null != param) log.info("与用户:{}断开链接", param.getIpAddr());
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("发送用户handler发生异常", cause);
        SymbolChannelParam param = WebsocketClientStore.removeChannel(ctx.channel());
        if (null != param) log.error("异常用户:{}", param.getIpAddr());
        ctx.close();
    }
}
*/
