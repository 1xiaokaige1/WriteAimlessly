package com.xiaokaige;

import lombok.Data;

import java.util.List;

/**
 * @author: zk
 * Date: 2022/7/13
 * Time: 9:43
 */
@Data
public class SymbolUpdateBO {
    //信号账户id
    private Long signalAccountId;

    //新增交易品种数量
    private Integer newCnt;

    //删除交易品种数量
    private Integer removedCnt;

    //跟随账户集合
    List<MismatchBO> mismatchList;

    @Data
    public static class MismatchBO{
        //跟随账户id
        private Long followAccountId;

        //不匹配的品种数目
        private Integer mismatchCnt;
    }
}
