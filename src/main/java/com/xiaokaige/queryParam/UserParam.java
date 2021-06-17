package com.xiaokaige.queryParam;

import com.xiaokaige.annotataion.MyselfDefineAnnotation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author zengkai
 * @date 2021/6/15 13:42
 */
@Data
public class UserParam {

    @NotNull
    @ApiModelProperty(value = "用户账号",required = true)
    @MyselfDefineAnnotation
    private Long userId;

    @Min(value = 18)
    @ApiModelProperty(value = "用户年龄",required = true)
    private Integer age;

    @NotNull
    @ApiModelProperty(value = "用户住址",required = true)
    private String address;
}
