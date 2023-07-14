package com.xiaokaige.exception;

import com.xiaokaige.enums.SubCodeEnum;

/**
 * @author: zk
 * Date: 2022/1/21
 * Time: 14:59
 */
public enum FriendEnum implements SubCodeEnum {
    FRIEND_NOT_EXIST("002", "好友不存在"),
    ;

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

    FriendEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
