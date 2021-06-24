package com.xiaokaige.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zk
 * @since 2021-06-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_info")
public class UserInfoDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户token数据
     */
    private String token;

    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String mail;

    private String phone;

    /**
     * 用户性别，1:男，2:女
     */
    private String gender;

    /**
     * 用户签名
     */
    private String signature;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
