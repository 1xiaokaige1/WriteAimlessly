package com.xiaokaige.controller;

/**
 * @Created with IDEA
 * @author: Super Zheng
 * @Description: java类作用描述
 * @Date:2018/12/26
 * @Time:12:20
 */
public class GitHubConstant {
    // 这里填写在GitHub上注册应用时候获得 CLIENT ID
    public static final String  CLIENT_ID="598d964e9e537afdff6c";
	//这里填写在GitHub上注册应用时候获得 CLIENT_SECRET
    public static final String CLIENT_SECRET="ded12bb0ad7b3764eaa12c6772b277b495b5bda4";
    // 回调路径
    public static final String CALLBACK = "http://localhost/callback";

    //获取code的url
    public static final String CODE_URL = "https://github.com/login/oauth/authorize?client_id="+CLIENT_ID+"&state=STATE&redirect_uri="+CALLBACK+"";
    //获取token的url
    public static final String TOKEN_URL = "https://github.com/login/oauth/access_token?client_id="+CLIENT_ID+"&client_secret="+CLIENT_SECRET+"&code=CODE&redirect_uri="+CALLBACK+"";
    //获取用户信息的url
    public static final String USER_INFO_URL = "https://api.github.com/user?access_token=TOKEN";

}

