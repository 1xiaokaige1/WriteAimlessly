/*
package com.xiaokaige.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

*/
/**
 * @author ly
 * @date 2022/5/5
 *//*


public class WebsocketSecurityHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WebsocketSecurityHandler.class);

    */
/*用户id*//*

    private static final String HEADER_UID = "uid";
    */
/*时间戳（毫秒级）*//*

    private static final String HEADER_TIMESTAMP = "timestamp";
    */
/*随机数，8位，与时间戳一起控制一个唯一的请求*//*

    private static final String HEADER_RANDOM = "random";
    */
/*32位MD5加密串，加密内容：uid-token-timestamp-password-random*//*

    private static final String HEADER_MD5 = "md5";

    private static final String HEADER_CLIENT_TYPE = "clientType";

    private final RequestAuthInterceptor interceptor;

    private static final String URL_AND_PARAM_DELIMITER = "?";
    private static final String PARAM_DELIMITER = "&";
    private static final String PARAM_AND_VAL_DELIMITER = "=";
    private static final String URL_ERROR = "Url Error";

    public WebsocketSecurityHandler(RequestAuthInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();
            if (!uri.contains(URL_AND_PARAM_DELIMITER)) {
                paramErrorDeal(ctx);
                return;
            }

            String paramStr = uri.substring(uri.indexOf(URL_AND_PARAM_DELIMITER) + 1);
            if (!StringUtils.hasText(paramStr)) {
                paramErrorDeal(ctx);
                return;
            }

            Map<String, String> paramMap = new HashMap<>();
            for (String str : paramStr.split(PARAM_DELIMITER)) {
                String[] split = str.split(PARAM_AND_VAL_DELIMITER);
                if (split.length < 2) {
                    paramErrorDeal(ctx);
                    return;
                }
                paramMap.put(split[0], split[1]);
            }

            String uid = paramMap.get(HEADER_UID);
            String timestamp = paramMap.get(HEADER_TIMESTAMP);
            String random = paramMap.get(HEADER_RANDOM);
            String md5 = paramMap.get(HEADER_MD5);
            String clientType = paramMap.get(HEADER_CLIENT_TYPE);
            if (checkParam(uid, timestamp, random, md5)) {
                // log.info("websocket链接参数异常 uid:{}, timestamp:{}, random:{}, md5:{}, clientType:{}", uid, timestamp, random, md5, clientType);
                paramErrorDeal(ctx);
                return;
            }
            boolean invalid = interceptor.isInvalid(null, md5, Long.parseLong(uid),
                    Long.parseLong(timestamp), random, Integer.parseInt(Optional.ofNullable(clientType).orElse(ClientTypeEnum.UNKNOWN.getCode().toString())));
            if (invalid) {
                // log.info("websocket链接参数校验失败 uid:{}, timestamp:{}, random:{}, md5:{}, clientType:{}", uid, timestamp, random, md5, clientType);
                paramErrorDeal(ctx);
                return;
            }

            // 获取ip
            String clientIp = request.headers().get("X-Forwarded-For");
            if  (clientIp ==  null ) {
                InetSocketAddress intSocket = (InetSocketAddress) ctx.channel()
                        .remoteAddress();
                clientIp = intSocket.getAddress().getHostAddress();
            }
            WebsocketClientStore.addInitParam(ctx.channel(), clientIp);
        }
        ctx.channel().pipeline().remove(this);
        super.channelRead(ctx, msg);
    }

    public boolean checkParam(String uid, String timestamp, String random, String md5) {
        return StringUtils.isEmpty(uid) ||
                StringUtils.isEmpty(timestamp) ||
                StringUtils.isEmpty(random) ||
                StringUtils.isEmpty(md5);
    }

    private void paramErrorDeal(ChannelHandlerContext ctx) {
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer(URL_ERROR, StandardCharsets.UTF_8));
        ctx.close();
    }
}
*/
