package com.xiaokaige.nettyClient;

import com.stl.util.STLJsonUtils;
import io.netty.channel.Channel;

/**
 * @author: zk
 * Date: 2022/9/7
 * Time: 13:41
 */
public class ClientOne {
    public static void main(String[] args) throws InterruptedException {
        //获取与服务端的连接通道
        NettyClient nettyClient = new NettyClient();
        nettyClient.connect();
        Channel channel = nettyClient.getChannel();
        PkgDataBean bean = new PkgDataBean();
        bean.setCmd((byte) 0x06);
        LoginInfoDTO loginInfoDTO = new LoginInfoDTO();
        loginInfoDTO.setAppId("123");
        loginInfoDTO.setDeviceId("abc");
        loginInfoDTO.setPlatform(1);
        loginInfoDTO.setRegId("1231662607135234755");
        loginInfoDTO.setPhoneModel("华为");
        String jsonStr = STLJsonUtils.obj2json(loginInfoDTO);
        bean.setData(jsonStr);
        bean.setDataLength((short)bean.getData().getBytes().length);
        // 写入数据1
        channel.writeAndFlush(bean);
    }
}
