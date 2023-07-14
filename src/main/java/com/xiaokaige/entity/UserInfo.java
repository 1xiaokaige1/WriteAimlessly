package com.xiaokaige.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author White
 * @date 2021-02-06
 */
@Component
@Data
@TableName("user_base")
public class UserInfo {

    /**
     * 用户Id
     */
    @TableId(value = "userId", type = IdType.AUTO)
    private Integer userId;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickName;

    /**
     * 汇聊号
     */
    @TableField("fxcode")
    private Integer fxCode;

    /**
     * 用户头像
     */
    @TableField("userHeadImg")
    private String userHeadImg;

    /**
     * 数据更新时间
     */
    @TableField("updateTime")
    private Date updateTime;

    /**
     * 用户类型
     */
    @TableField("userType")
    private Integer userType;

    /**
     * 加密手机号
     */
    @TableField("phoneNumberEncrypt")
    private String phoneNumberEncrypt;

    /**
     * 加密邮箱
     */
    @TableField("emailEncrypt")
    private String emailEncrypt;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 交易格言
     */
    @TableField("tradingMaxims")
    private String tradingMaxims;

    /**
     * 用户背景图
     */
    @TableField("userCenterImg")
    private String userCenterImg;

    /**
     * 注册时间
     */
    @TableField("registerTime")
    private Date registerTime;

    /**
     * 手机号所属国家编码
     */
    @TableField("phoneCountry")
    private String phoneCountry;

    /**
     * 是否默认密码，默认=0，已设置=1
     */
    @TableField("defaultPassword")
    private Integer defaultPassword;

    /**
     * 用户选择的语言，zh-TW,en
     */
    @TableField("language")
    private String language;
}