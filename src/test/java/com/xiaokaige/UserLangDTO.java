package com.xiaokaige;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author: zk
 * Date: 2022/8/25
 * Time: 10:15
 */
@Data
public class UserLangDTO implements Serializable {
    //用户融云id
    private String cloudUserId;

    //用户语言标识
    private Integer lang;

    //用户id
    private Long userId;

    public static void main(String[] args) {
        String uid = UUID.randomUUID().toString();
        StringUtils.replace(uid,"-", "");
        System.out.println("uid = " + uid);
    }
}
