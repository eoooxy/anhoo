package com.anhoo.util;

import com.anhoo.common.BaseBean;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/16
 * Time  16:04
 */

public class RedisCache extends BaseBean implements Cache {

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private String id;
    private static JedisConnectionFactory jedisConnectionFactory;

    public RedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        logger.debug("---------------------Mybatis RedisCache:id=" + id + "---------------------");
        this.id = id;
    }

    /**
     * 清除所有数据
     */
    @Override
    public void clear() {
        JedisConnection connection = null;
        try {
            connection = (JedisConnection) jedisConnectionFactory.getConnection();
            connection.flushAll();
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * @return
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * 得到指定key的 value
     * @param key
     * @return object
     */
    @Override
    public Object getObject(Object key) {
        Object result = null;
        JedisConnection connection = null;
        try {
            connection = (JedisConnection) jedisConnectionFactory.getConnection();
            result = SerializeUtil.unserialize(connection.get(SerializeUtil.serialize(key)));
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    /**
     * 得到当前db的key值
     * @return int
     */
    @Override
    public int getSize() {
        int result = 0;
        JedisConnection connection = null;
        try {
            connection = (JedisConnection) jedisConnectionFactory.getConnection();
            result = Integer.valueOf(connection.dbSize().toString());
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    /**
     * 写入 key-value
     * @param key
     * @param value
     */
    @Override
    public void putObject(Object key, Object value) {
        JedisConnection connection = null;
        try {
            logger.debug("------------------Redis Put Object:" + key.toString() + ":" + value.toString() + "-------------------");
            connection = (JedisConnection) jedisConnectionFactory.getConnection();
            connection.set(SerializeUtil.serialize(key), SerializeUtil.serialize(value));
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * 删除指定key的值
     * @param key
     * @return
     */
    @Override
    public Object removeObject(Object key) {
        JedisConnection connection = null;
        Object result = null;
        try {
            connection = (JedisConnection) jedisConnectionFactory.getConnection();
            result = connection.expire(SerializeUtil.serialize(key), 0);
            //or  result = connection.del(SerializeUtil.serialize(key));
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.jedisConnectionFactory = jedisConnectionFactory;
    }
}
