package com.xiaokaige.redis;

import io.lettuce.core.ReadFrom;
import io.lettuce.core.models.role.RedisInstance;
import io.lettuce.core.models.role.RedisNodeDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.xiaokaige.redis.RedisWorkGroupAddrEnum.gerRedisHostPrefix;

public final class GERRedisStrategy extends ReadFrom {

    private static final Logger log = LoggerFactory.getLogger(GERRedisStrategy.class);
    private boolean flag = true;

    @Override
    public List<RedisNodeDescription> select(Nodes nodes) {
        List<RedisNodeDescription> redisNodeDescriptions = nodes.getNodes().stream()
                .filter(e -> e.getUri().getHost().startsWith(gerRedisHostPrefix()) && e.getRole().equals(RedisInstance.Role.SLAVE))
                .collect(Collectors.toList());
        if (flag) {
            log.warn("德国节点信息 =>{}", nodes.getNodes());
            flag = false;
        }

        if (CollectionUtils.isEmpty(redisNodeDescriptions)) {
            log.warn("选择的链接为主机");
            return nodes.getNodes().stream().filter(e -> e.getRole().equals(RedisInstance.Role.MASTER)).collect(Collectors.toList());
        }
        // log.warn("选择的链接为德国从机 =>{}", redisNodeDescriptions);
        return redisNodeDescriptions;
    }
}