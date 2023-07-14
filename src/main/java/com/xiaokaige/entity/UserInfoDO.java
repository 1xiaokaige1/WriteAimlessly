package com.xiaokaige.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_token")
@Data
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

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getSignature() {
        return signature;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

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
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;


}
