package com.xiaokaige.annotataion;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zengkai
 * @date 2021/6/15 16:38
 */
@Target({ElementType.PARAMETER,ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckUserImpl.class)
public @interface MyselfDefineAnnotation {
    /**
     * 是否允许为空
     * @return
     */
    boolean allowNull() default false;

    /**
     * 是否需要校验
     * @return
     */
    boolean doValid() default true;

    /**
     * 提示信息
     *
     * @return
     */
    String message() default "用户id不存在";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 是否隐藏参数
     *
     * @return
     */
    boolean hideDescription() default false;

}
