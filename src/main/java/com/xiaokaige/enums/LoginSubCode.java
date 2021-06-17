package com.xiaokaige.enums;

import lombok.AllArgsConstructor;

/**
 * @author zengkai
 * @date 2021/6/17 16:24
 */
@AllArgsConstructor
public enum LoginSubCode implements SubCodeEnum{

    /**
     * 用户登录状态码
     */
    LOGIN_SUCCESS_CODE("AC0010","用户登录成功!"),
    LOGIN_FAILURE_CODE("AC0011","用户登录失败!");

    String code;
    String desc;

    @Override
    public String getSubCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
