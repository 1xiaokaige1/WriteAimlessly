package com.xiaokaige.nettyClient;

import com.itlgl.java.util.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ClientEncoder extends MessageToByteEncoder<PkgDataBean> {

    private static final String TAG = "ClientEncoder";

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, PkgDataBean data, ByteBuf byteBuf) throws Exception {
        //根据数据包协议，生成byte数组
        byte[] bytes = {0x2A, data.getCmd()};
        short number  = data.getDataLength();
        byte[] temp = new byte[2];
        temp[0] = (byte) (number >> 8);
        temp[1] = (byte) (number);  //short转字节数组
        byte[] dataBytes = data.getData().getBytes();
        //将所有数据合并成一个byte数组
        byte[] all = ByteUtils.combine(bytes, temp, dataBytes, new byte[]{0x2A});
        //发送数据
        byteBuf.writeBytes(all);
    }
}

