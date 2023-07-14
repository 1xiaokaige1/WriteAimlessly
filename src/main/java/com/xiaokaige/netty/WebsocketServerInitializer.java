/*
package com.xiaokaige.netty;
*/
/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *//*




public class WebsocketServerInitializer extends ChannelInitializer<SocketChannel> {

    private final RequestAuthInterceptor interceptor;

    private final NettyProperties properties;

    public WebsocketServerInitializer(RequestAuthInterceptor interceptor, NettyProperties properties) {
        this.interceptor = interceptor;
        this.properties = properties;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("httpCodec", new HttpServerCodec());
        pipeline.addLast("chunkedHandler", new ChunkedWriteHandler());
        pipeline.addLast("httpAggregator", new HttpObjectAggregator(65536));
        pipeline.addLast("websocketSecurityHandler", new WebsocketSecurityHandler(interceptor));
        pipeline.addLast("websocketProtocolHandler", new WebSocketServerProtocolHandler(properties.getPath(), Boolean.TRUE));
        pipeline.addLast("websocketCmdHandler", new WebsocketCmdHandler());
        pipeline.addLast("websocketHandler", new WebSocketFrameHandler());
    }
}
*/
