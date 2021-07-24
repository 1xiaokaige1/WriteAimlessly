package com.xiaokaige.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zengkai
 * @date 2021/7/2 8:56
 */
@ConfigurationProperties(prefix = "rongcloud.im")
@Data
@Component
public class RongConfig {
    private String appKey;

    private String appSecret;
}
