/*
package com.xiaokaige.netty;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

*/
/**
 * @author ly
 * @date 2022/5/11
 *//*

public class WebsocketClientStore {

    private static final Map<Channel, SymbolChannelParam> paramMap = new ConcurrentHashMap<>();

    public static void addInitParam(Channel channel, String ipAddr) {
        paramMap.put(channel, SymbolChannelParam.init(ipAddr));
    }

    public static SymbolChannelParam getParam(Channel channel) {
        return paramMap.get(channel);
    }

    public static void updateSymbolIds(Channel channel, Set<String> symbolIds) {
        SymbolChannelParam param = paramMap.get(channel);
        if (null != param) param.setSymbol(symbolIds);
    }

    public static SymbolChannelParam removeChannel(Channel channel) {
        return paramMap.remove(channel);
    }
}
*/
