package com.xiaokaige.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zengkai
 * @date 2021/6/30 17:30
 */
@Component
@ConfigurationProperties(prefix = "stl.project")
@Data
public class StlConfig {

    private String flag;
}
