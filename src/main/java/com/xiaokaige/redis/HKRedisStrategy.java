package com.xiaokaige.redis;

import io.lettuce.core.ReadFrom;
import io.lettuce.core.models.role.RedisInstance;
import io.lettuce.core.models.role.RedisNodeDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.xiaokaige.redis.RedisWorkGroupAddrEnum.hkRedisHostPrefix;

public final class HKRedisStrategy extends ReadFrom {

    private static final Logger log = LoggerFactory.getLogger(HKRedisStrategy.class);
    private boolean flag = true;

    @Override
    public List<RedisNodeDescription> select(Nodes nodes) {
        List<RedisNodeDescription> redisNodeDescriptions = nodes.getNodes().stream()
                .filter(e -> e.getUri().getHost().startsWith(hkRedisHostPrefix()) && e.getRole().equals(RedisInstance.Role.SLAVE))
                .collect(Collectors.toList());
        if (flag) {
            log.warn("香港节点信息 =>{}", nodes.getNodes());
            flag = false;
        }

        if (CollectionUtils.isEmpty(redisNodeDescriptions)) {
            log.warn("选择的链接为主机");
            return nodes.getNodes().stream().filter(e -> e.getRole().equals(RedisInstance.Role.MASTER)).collect(Collectors.toList());
        }
        // log.warn("选择的链接为香港从机 =>{}", redisNodeDescriptions);
        return redisNodeDescriptions;
    }
}