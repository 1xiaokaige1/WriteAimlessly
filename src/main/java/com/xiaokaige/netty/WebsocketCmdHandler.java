/*
package com.xiaokaige.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

*/
/**
 * @author ly
 * @date 2022/5/11
 *//*

public class WebsocketCmdHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static final Logger log = LoggerFactory.getLogger(WebsocketCmdHandler.class);

    @Getter
    @AllArgsConstructor
    public enum WebsocketMessageType {
        */
/*client*//*

        PING("ping"),
        SUB("subscribe"),
        */
/*server*//*

        PONG("pong"),
        PUSH("push"),
        ;

        private final String msg;

        public static final Map<String, WebsocketMessageType> enumMap =
                Arrays.stream(WebsocketMessageType.values())
                        .collect(Collectors.toMap(WebsocketMessageType::getMsg, Function.identity()));

        public static WebsocketMessageType of(String msg){
            return enumMap.get(msg);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 这里就是前端会发订阅id和ping
        WebsocketMessageModel message = JsonUtil.toObject(msg.text(), WebsocketMessageModel.class);
        if (null == message) return;
        String cmd = message.getCmd();
        WebsocketMessageType type = WebsocketMessageType.of(cmd);
        switch (type) {
            case PING:
                // ping 命令
                SymbolChannelParam pingParam = WebsocketClientStore.getParam(ctx.channel());
                if (null == pingParam) return;
                pingParam.setLastTime(System.currentTimeMillis());
                WebsocketMessageModel pongFrame = WebsocketMessageModel.of(WebsocketMessageType.PONG.getMsg(), null);
                ctx.channel().writeAndFlush(new TextWebSocketFrame(JsonUtil.toJson(pongFrame)));
                break;
            case SUB:
                // 订阅命令
                String body = message.getBody();
                Set<String> symbolIds = JsonUtil.toSet(body, String.class);
                if (CollectionUtils.isEmpty(symbolIds)) return;
                WebsocketClientStore.updateSymbolIds(ctx.channel(), symbolIds);
                break;
            default:
                break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("响应handler发生异常 =>", cause);
        SymbolChannelParam param = WebsocketClientStore.removeChannel(ctx.channel());
        if (null != param) log.error("异常用户:{}", param.getIpAddr());
        ctx.close();
    }
}
*/
