package com.xiaokaige.queryParam;

import lombok.Data;

import java.util.List;

/**
 * @author: zk
 * Date: 2022/10/2
 * Time: 14:03
 */
@Data
public class AddPushMessageParam {

    private Long operateUid;

    private String operator;

    private Integer langId;

    private Integer contentType;

    private String contentData;

    private String title;

    private String content;

    private Long sendUid;

    private int noticeType;

    private List<Long> receiveUids;

    private String sendTime;

    private boolean pushAll;

    private boolean syncToPersonalMessage;

}
