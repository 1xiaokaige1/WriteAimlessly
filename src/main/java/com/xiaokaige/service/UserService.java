package com.xiaokaige.service;

import com.xiaokaige.config.RongCloudIMProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.rong.RongCloud;
import io.rong.methods.user.User;
import io.rong.models.Result;
import io.rong.models.response.TokenResult;
import io.rong.models.response.UserResult;
import io.rong.models.user.UserModel;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
	private RongCloudIMProperties rongCloudIMProperties;
    
    /**
     * 自定义api地址
     */
    @SuppressWarnings("unused")
	private static String api = "http://api-cn.ronghub.com";

    /**
     * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/user/user.html#register
         *  注册用户，生成用户在融云的唯一身份标识 Token
     * @param 	userModel id、name、portrait三个参数必传
     * @return  Result
     */
    public TokenResult getToken(UserModel userModel) throws Exception {
    	RongCloud rongCloud;

    	rongCloud = RongCloud.getInstance(rongCloudIMProperties.getAppKey(), rongCloudIMProperties.getAppSecret());

    	// 自定义 api 地址方式
    	// RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);
    	// 使用 百度 HTTPDNS 获取最快的 IP 地址进行连接
    	// BaiduHttpDNSUtil.setHostTypeIp("account_id", "secret", rongCloud.getApiHostType());
    	
    	// 设置连接超时时间
    	// rongCloud.getApiHostType().setConnectTimeout(10000);
    	// 设置读取超时时间
    	// rongCloud.getApiHostType().setReadTimeout(10000);
    	// 获取备用域名List
    	// List<HostType> hosttypes = rongCloud.getApiHostListBackUp();
    	// 设置连接、读取超时时间
    	// for (HostType hosttype : hosttypes) {
    	//     hosttype.setConnectTimeout(10000);
    	//     hosttype.setReadTimeout(10000);
    	// }
    	User user = rongCloud.user;
    	TokenResult result = user.register(userModel);
    	logger.info("getToken:  " + result.toString());
    	return result;
    }
  
}
