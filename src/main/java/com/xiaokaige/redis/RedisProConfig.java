package com.xiaokaige.redis;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import io.lettuce.core.ReadFrom;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Profile({"ger", "hk", "dev", "test", "stage", "prod"})
@Slf4j
@Configuration
@EnableCaching
public class RedisProConfig {

    @Autowired
    private RedisSentinelConfig redisSentinelConfig;

    @Value("${spring.profiles.active}")
    private String ACTIVE;

    @Bean(name = "jsonRedisTemplate")
    public RedisTemplate<String, Object> jsonRedisTemplate(@Qualifier("masterLettuceConnectionFactory") LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());//key序列化
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean(name = "redisTemplate")
    public StringRedisTemplate redisTemplate(@Qualifier("masterLettuceConnectionFactory") RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate();
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        template.setConnectionFactory(factory);
        //key序列化方式
        template.setKeySerializer(redisSerializer);
        //value序列化
        template.setValueSerializer(redisSerializer);
        //value hashmap序列化
        template.setHashValueSerializer(redisSerializer);
        //key haspmap序列化
        template.setHashKeySerializer(redisSerializer);
        //
        return template;
    }

    @Bean(name = "masterRedisTemplate")
    public StringRedisTemplate masterRedisTemplate(@Qualifier("masterReadWriteLettuceConnectionFactory") RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(factory);
        return template;
    }

    /*@Bean(name = "masterLettuceConnectionFactory")
    @Primary
    public LettuceConnectionFactory masterLettuceConnectionFactory(@Qualifier("redisSentinelConfiguration") RedisSentinelConfiguration sentinelConfiguration,
                                                                   @Qualifier("poolConfig") GenericObjectPoolConfig<?> poolConfig) {
        LettuceClientConfiguration.LettuceClientConfigurationBuilder builder = LettucePoolingClientConfiguration.builder().poolConfig(poolConfig);
        LettuceClientConfiguration clientConfiguration = builder.readFrom(RedisChooseStrategy.choose(ACTIVE)).build();
        return new LettuceConnectionFactory(sentinelConfiguration, clientConfiguration);
    }*/

    /**
     * 此工厂专门负责读写主库
     */
    @Bean(name = "masterReadWriteLettuceConnectionFactory")
    public LettuceConnectionFactory masterReadWriteLettuceConnectionFactory(@Qualifier("redisSentinelConfiguration") RedisSentinelConfiguration sentinelConfiguration,
                                                                            @Qualifier("poolConfig") GenericObjectPoolConfig<?> poolConfig) {
        LettuceClientConfiguration.LettuceClientConfigurationBuilder builder = LettucePoolingClientConfiguration.builder().poolConfig(poolConfig);
        LettuceClientConfiguration clientConfiguration = builder.readFrom(ReadFrom.MASTER_PREFERRED).build();
        return new LettuceConnectionFactory(sentinelConfiguration, clientConfiguration);
    }

    @Bean
    public GenericObjectPoolConfig<?> poolConfig(RedisProperties properties) {
        GenericObjectPoolConfig<?> poolConfig = new GenericObjectPoolConfig<>();
        RedisProperties.Pool poolCof = properties.getLettuce().getPool();
        poolConfig.setMaxIdle(poolCof.getMaxIdle());
        poolConfig.setMinIdle(poolCof.getMinIdle());
        poolConfig.setMaxTotal(poolCof.getMaxActive());
        poolConfig.setMaxWaitMillis(poolCof.getMaxWait().toMillis());
        return poolConfig;
    }

    @Bean
    public RedisSentinelConfiguration redisSentinelConfiguration() {
        if (redisSentinelConfig == null || StringUtils.isEmpty(redisSentinelConfig.getNodes())) {
            //log.error("【缺乏redis配置】：{}", JsonUtil.toJson(redisSentinelConfig));
        }

        Set<String> setRedisNode = new HashSet<>(Arrays.asList(redisSentinelConfig.getNodes().split(",")));
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration(redisSentinelConfig.getMaster(), setRedisNode);
        redisSentinelConfiguration.setDatabase(redisSentinelConfig.getDatabase());
        redisSentinelConfiguration.setPassword(redisSentinelConfig.getPassword());
        return redisSentinelConfiguration;
    }


}
