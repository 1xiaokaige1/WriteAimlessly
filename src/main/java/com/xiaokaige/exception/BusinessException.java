package com.xiaokaige.exception;

import com.xiaokaige.enums.SubCodeEnum;
import lombok.Getter;

/**
 * @author: zk
 * Date: 2022/1/21
 * Time: 14:47
 */
@Getter
public class BusinessException extends RuntimeException {

    private String code;

    private SubCodeEnum subCodeEnum;

    public BusinessException(SubCodeEnum subCodeEnum) {
        super(subCodeEnum.getDesc());
        this.code = subCodeEnum.getSubCode();
        this.subCodeEnum = subCodeEnum;
    }
}
