/*
package com.xiaokaige.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xiaokaige.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/user")
@CrossOrigin
public class UserController{

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	*/
/**
	 * 根据userId,userName获取token
	 * @param userId
	 * @param userName
	 * @return
	 *//*

    @GetMapping("/getToken")
    public String getToken(String userId,String userName) {
    	logger.info("enter into getToken function......");
    	logger.info("======userId:{}=======userName:{}", userId, userName);
    	if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(userName)) {
    		return responseJson("99","userId、userName请求参数都不能为空",null);
    	}
    	UserModel userModel = new UserModel();
    	userModel.setId(userId);
    	userModel.setName(userName);
    	userModel.setPortrait("http://www.rongcloud.cn/images/logo.png");
    	TokenResult result;
		try {
			result = userService.getToken(userModel);
		} catch (Exception e) {
			logger.error("获取 Token 失败 ", e);
			return responseJson("999","获取 Token 失败",null);
		}
    	return responseJson("00","请求成功",result);
    }

	public String responseJson(String code,String msg,Object obj){
    	Map<String, Object> res = new HashMap<>();
		res.put("code", code);
		res.put("msg", msg);
		if (obj == null) {
			obj = new Object();
		}
		res.put("data", obj);
		String ret = JSONObject.toJSONString(res, SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.PrettyFormat,SerializerFeature.SortField);
		logger.info("RESPONSE:" + ret);
		return ret;
	}
}
*/
