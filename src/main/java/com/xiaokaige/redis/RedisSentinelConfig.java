package com.xiaokaige.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author jiahui
 * @date 2021/7/29 19:49
 */
@Profile({"ger","hk","dev","test","stage","prod"})
@Component
@ConfigurationProperties(prefix="spring.redis.sentinel")
@Data
public class RedisSentinelConfig {
    String master;
    String nodes;
    String password;
    Integer database;
}