package com.xiaokaige.strategy.anno;

import com.xiaokaige.strategy.enums.DefineEnum;

import java.lang.annotation.*;

/**
 * @author zengkai
 * @date 2021/8/4 16:54
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DefineAnnotation {
    DefineEnum value();
}
