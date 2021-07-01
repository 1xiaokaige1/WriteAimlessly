package com.xiaokaige.annotataion;

import com.xiaokaige.enums.TestCodeEnum;

import java.lang.annotation.*;

/**
 * @author zengkai
 * @date 2021/6/29 9:05
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface StrategyAno {
    TestCodeEnum value();
}
