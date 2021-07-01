package com.xiaokaige;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author zengkai
 * @date 2021/6/15 10:59
 */
@SpringBootApplication
@EnableConfigurationProperties
@MapperScan(basePackages = "com.xiaokaige.dao")
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
