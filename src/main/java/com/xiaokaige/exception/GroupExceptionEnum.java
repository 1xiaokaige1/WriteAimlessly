package com.xiaokaige.exception;

import com.xiaokaige.enums.SubCodeEnum;

/**
 * @author: zk
 * Date: 2022/1/21
 * Time: 15:24
 */
public enum GroupExceptionEnum implements SubCodeEnum {

    GROUP_NOT_EXIST("001", "群不存在"),
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

    GroupExceptionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
