package com.xiaokaige.nettyClient;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class ClientDecoder extends ByteToMessageDecoder {

    public static final Logger logger = LoggerFactory.getLogger(ClientDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int length = byteBuf.readableBytes();
        //收到的数据包
        byte[] data = new byte[length];
        byteBuf.duplicate().readBytes(data);
        //判断数据包是不是一个正确的数据包
        if (data[0] == 0x2A && data[0] == data[data.length - 1]) {
            PkgDataBean bean = new PkgDataBean();
            bean.setCmd(data[1]);
            short temp0 = data[2];
            short temp1 = data[3];
            short tempRes = (short) (temp0 << 8);
            short result = (short) (tempRes + (temp1 > 0 ? temp1 : temp1 & 0x00ff));//字节数组转short
            bean.setDataLength(result);
            byte[] bytes = Arrays.copyOfRange(data, 4, 4 + bean.getDataLength());
            bean.setData(new String(bytes));
            logger.info("收到了服务端发送的数据:" + bean);
            list.add(bean);
            byteBuf.skipBytes(byteBuf.readableBytes());
        }
    }
}