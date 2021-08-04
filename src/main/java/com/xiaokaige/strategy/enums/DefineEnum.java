package com.xiaokaige.strategy.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zengkai
 * @date 2021/8/4 16:57
 */

public enum DefineEnum {
    /**
     * 策略一
     */
    STRATEGY_ONE(1),
    /**
     * 策略二
     */
    STRATEGY_TWO(2),
    /**
     * 策略三
     */
    STRATEGY_THREE(3);

    Integer code;

    DefineEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    private static Map<Integer, DefineEnum> map = new HashMap<>();

    static {

        for (DefineEnum defineEnum : DefineEnum.values()) {
            map.put(defineEnum.getCode(), defineEnum);
        }


    }

    public static DefineEnum getDefineEnum(Integer code) {
        return map.get(code);
    }
}
