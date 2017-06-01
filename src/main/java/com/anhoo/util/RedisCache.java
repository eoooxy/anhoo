package com.anhoo.util;

import com.anhoo.common.BaseBean;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheKey;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
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
     *
     * @param key
     * @return object
     */
    @Override
    public Object getObject(Object key) {
        Object result = null;
        JedisConnection connection = null;
//        CacheKey cacheKey = (CacheKey) key;
//        Field field = null;
        try {
//            //根据反射 得到Cachekey中的updateList 判断方法名，如果包含方法名字包含 update字眼的，那么删掉缓存key,直接让从数据库中读取
//            field = cacheKey.getClass().getDeclaredField("updateList");
//            field.setAccessible(true);
//            List<Object> list = (List<Object>) field.get(cacheKey);
//            String[] array = ( list.get(0).toString()).split("\\.");
//            String methodName = array[array.length-1];
//            //判断方法名字 是否带有update 如果带有 强制让他从数据库中取
//            if (methodName.contains("update")) {
//                connection.del(SerializeUtil.serialize(key));
//                return result;
//            }
            connection = (JedisConnection) jedisConnectionFactory.getConnection();
            result = SerializeUtil.unserialize(connection.get(SerializeUtil.serialize(key)));
//            result = SerializeUtil.unserialize(connection.hGet(RedisCache.this.id.toString().getBytes(),
//                    key.toString().getBytes()));
        } catch (JedisConnectionException e) {
            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    /**
     * 得到当前db的key值
     *
     * @return int
     */
    @Override
    public int getSize() {
        int result = 0;
        JedisConnection connection = null;
        try {
            connection = (JedisConnection) jedisConnectionFactory.getConnection();
            result = Integer.valueOf(connection.dbSize().toString());
//            result = Integer.valueOf(connection.hGetAll(RedisCache.this.id.toString().getBytes()).size());
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
     *
     * @param key
     * @param value
     */
    @Override
    public void putObject(Object key, Object value) {
        JedisConnection connection = null;
        try {
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
     *
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
            //或者  result = connection.del(SerializeUtil.serialize(key));
//            result = connection.hDel(RedisCache.this.id.toString().getBytes(),key.toString().getBytes());
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

    /**
     * 注入jedisConnectionFactory
     *
     * @param jedisConnectionFactory
     */
    public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.jedisConnectionFactory = jedisConnectionFactory;
    }
}
