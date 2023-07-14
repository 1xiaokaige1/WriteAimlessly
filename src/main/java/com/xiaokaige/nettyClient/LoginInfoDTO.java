package com.xiaokaige.nettyClient;

import lombok.Data;

/**
 * @author: zk
 * Date: 2022/9/7
 * Time: 17:01
 */
@Data
public class LoginInfoDTO {
    /**
     * 应用唯一标识
     */
    private String appId;

    /**
     * 注册id，为null时服务端生成
     */
    private String regId;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 手机型号
     */
    private String phoneModel;

    /**
     * 平台号，1：Android;2：IOS
     */
    private Integer platform;
}
