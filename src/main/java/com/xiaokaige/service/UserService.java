package com.xiaokaige.service;

import com.xiaokaige.queryParam.UserParam;

public interface UserService {

	String getToken(String uid, String nickname, String portrait, String flag);

	void updateUserInfo(String uid, UserParam userParam);

}
