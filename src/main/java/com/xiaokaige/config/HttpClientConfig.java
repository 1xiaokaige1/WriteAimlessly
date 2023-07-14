package com.xiaokaige.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author: panxuan
 * @date: 2019/12/17 10:05
 * http client配置
 */
@Configuration
@ImportResource(locations = {"classpath:spring-httpclient.xml"})
public class HttpClientConfig {

}