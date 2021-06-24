package com.xiaokaige.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 群组信息表
 * </p>
 *
 * @author zk
 * @since 2021-06-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("group_info")
public class GroupInfoDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 群组ID
     */
    @TableId(value = "group_id")
    private String groupId;

    /**
     * 群组名称
     */
    private String groupName;

    /**
     * 群组描述
     */
    private String description;

    /**
     * 群主ID
     */
    private String groupUserId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
