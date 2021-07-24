package com.xiaokaige.rongcloud;

import io.rong.models.Result;
import io.rong.models.response.TokenResult;

/**
 * @author zengkai
 * @date 2021/7/2 8:54
 */
public interface RongClient {
    /**
     * 获取token 通过调用sdk的方式
     *
     * @param id 用户id
     * @param name     昵称
     * @param portrait 头像地址
     * @return
     * @throws Exception
     */
    TokenResult register(String id, String name, String portrait) throws Exception;


    /**
     * 修改用户信息
     *
     * @param encodeId 用户id
     * @param name     昵称
     * @param portrait 头像地址
     * @return
     * @throws Exception
     */
    Result updateUser(String encodeId, String name, String portrait) throws Exception;



}
