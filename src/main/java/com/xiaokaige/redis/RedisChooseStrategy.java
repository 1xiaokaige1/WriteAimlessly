package com.xiaokaige.redis;

import io.lettuce.core.ReadFrom;

/**
 * @author ly
 * @date 2022/4/15
 */

public class RedisChooseStrategy {

    /*public static ReadFrom choose(String dev) {
        if (getProdHk().equals(dev)) return new HKRedisStrategy();
        if (getProdGer().equals(dev)) return new GERRedisStrategy();
        return ReadFrom.REPLICA_PREFERRED;
        return null;
    }*/
}
