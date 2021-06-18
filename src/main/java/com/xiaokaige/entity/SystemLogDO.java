package com.xiaokaige.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zengkai
 * @date 2021/6/18 8:59
 */
@TableName(value = "log")
@Data
public class SystemLogDO implements Serializable {

    @TableId(value = "czrzxh")
    private Long logId;
    @TableField(value = "ip")
    private String ip;
    @TableField(value = "rzxq")
    private String logDetail;
    @TableField(value = "czrid")
    private String userId;
    @TableField(value = "czrxm")
    private String username;
    @TableField(value = "url")
    private String url;
    @TableField(value = "czsj")
    private Date date;
    @TableField(value = "zhxh")
    private Long tenantId;
    @TableField(value = "qqfs")
    private String method;
    @TableField(value = "fhjg")
    private String result;
    @TableField(value = "jkff")
    private String classMethod;
    @TableField(value = "qqcs")
    private String args;
    @TableField(value = "hfsj")
    private Long time;

    public SystemLogDO(String ip, String logDetail, String userId, String username, String url, Date date, String method, String result, String classMethod, String args, Long time) {
        this.ip = ip;
        this.logDetail = logDetail;
        this.userId = userId;
        this.username = username;
        this.url = url;
        this.date = date;
        this.method = method;
        this.result = result;
        this.classMethod = classMethod;
        this.args = args;
        this.time = time;
    }

}
