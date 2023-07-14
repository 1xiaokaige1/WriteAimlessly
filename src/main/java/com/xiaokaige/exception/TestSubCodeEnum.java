package com.xiaokaige.exception;

import com.xiaokaige.enums.SubCodeEnum;
import lombok.AllArgsConstructor;

/**
 * @author: zk
 * Date: 2022/1/21
 * Time: 14:55
 */
@AllArgsConstructor
public class TestSubCodeEnum implements SubCodeEnum {

    private String code;

    private String desc;


    @Override
    public String getSubCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
