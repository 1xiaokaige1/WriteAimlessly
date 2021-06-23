package com.xiaokaige.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 融云IM配置映射类
 */
@Component
@ConfigurationProperties(prefix = "rongcloud.im")
public class RongCloudIMProperties {

	private String appKey;
	 
	private String appSecret;
	 
	 
	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	@Override
    public String toString() {
	    return "RongCloudIMProperties{" +
	        "appKey:'" + appKey + '\'' +
	        ", appSecret:" + appSecret +
	        '}';
    } 
}
