package com.anhoo.util;

import org.apache.ibatis.cache.CacheException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/16
 * Time  16:12
 */

public final class SerializeUtil {
    /**
     * 序列化工具类
     */
    public SerializeUtil() {
    }

    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;

        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] e = baos.toByteArray();
            return e;
        } catch (Exception var4) {
            throw new CacheException(var4);
        }
    }

    public static Object unserialize(byte[] bytes) {
        if(bytes == null) {
            return null;
        } else {
            ByteArrayInputStream bais = null;

            try {
                bais = new ByteArrayInputStream(bytes);
                ObjectInputStream e = new ObjectInputStream(bais);
                return e.readObject();
            } catch (Exception var3) {
                throw new CacheException(var3);
            }
        }
    }
}
