package com.anhoo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/16
 * Time  16:30
 */

public class RedisCacheTransfer {

    @Autowired
    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
    }
}
