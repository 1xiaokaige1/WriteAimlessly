package com.xiaokaige.enums;

import lombok.AllArgsConstructor;

import java.util.HashMap;

/**
 * @author zengkai
 * @date 2021/6/16 9:20
 */
@AllArgsConstructor
public enum TestCodeEnum implements SubCodeEnum {


    /**
     * 测试枚举用例
     */
    TEST_SUCCESS_CODE("abc001", "测试成功!"),
    TEST_FAILURE_CODE("abc002", "测试失败!");

    String code;

    String desc;

    private static HashMap<String, TestCodeEnum> map = new HashMap<>();

    static {
        for (TestCodeEnum value : TestCodeEnum.values()) {
            map.put(value.getSubCode(), value);
        }
    }



    @Override
    public String getSubCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }


}
