package com.xiaokaige.enums;

import lombok.AllArgsConstructor;

/**
 * @author zengkai
 * @date 2021/6/16 9:33
 */
@AllArgsConstructor
public enum TestSubCode implements SubCodeEnum {

    /**
     * 测试用例
     */
    TEST_SUB_CODE_ONE("A0010","测试成功用例One"),
    TEST_SUB_CODE_TWO("A0011","测试用例Two"),
    TEST_SUB_CODE_THIRD("A0020","测试成功用例Third"),
    TEST_SUB_CODE_FOURTH("A0021","测试成功用例Fourth");

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
