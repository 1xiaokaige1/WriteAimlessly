package com.xiaokaige.nettyClient;

import lombok.Data;

@Data
public class PkgDataBean {
    //命令字
    private byte cmd;
    //数据长度
    private Short dataLength;
    //数据
    private String data;
}
