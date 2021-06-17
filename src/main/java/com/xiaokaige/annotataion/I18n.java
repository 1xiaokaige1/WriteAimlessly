package com.xiaokaige.annotataion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 国际化注解
 *
 * @author panxuan
 * @date 2021/4/13 9:38
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface I18n {

    String key();

    String[] params() default {};

}
