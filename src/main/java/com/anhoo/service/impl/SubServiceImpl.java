package com.anhoo.service.impl;

import com.alibaba.fastjson.JSON;
import com.anhoo.entity.MessageEntity;
import com.anhoo.service.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/22
 * Time  11:26
 */

@Service
public class SubServiceImpl implements SubService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    JedisConnectionFactory jedisConnFactory;

    @Override
    public void isCall(Message message) {
        MessageEntity messageEntity = new MessageEntity();
        //请参考配置文件，本例中key，value的序列化方式均为string。
        //其中key必须为stringSerializer。和redisTemplate.convertAndSend对应
        messageEntity.setUser(stringRedisTemplate.getStringSerializer().deserialize(message.getChannel()).split("_")[1]);
        messageEntity.setContent(stringRedisTemplate.getValueSerializer().deserialize(message.getBody()).toString());
        stringRedisTemplate.opsForList().leftPush("msgList", JSON.toJSONString(messageEntity));
//        Jedis jedis = (Jedis) jedisConnFactory.getConnection().getNativeConnection();
//        stringRedisTemplate.opsForValue().set("broadcast",jedis.pubsubNumPat().toString() );
//        System.out.println(jedis.pubsubNumPat().toString());
//        jedis.close();

    }
    @Override
    public MessageEntity callBack(String user) throws InterruptedException {

        //模拟1s 查看一次 不至于一直在连接redis 低于1s的频率连接redis会报错
        Thread.sleep(1000);
//            String msgTxt = stringRedisTemplate.opsForList().rightPop("msgList");
        //获取当前user 对应的消息 坐标值
        Double index = stringRedisTemplate.opsForZSet().score("userList", user);

        long l = new Double(index).longValue();
        if (stringRedisTemplate.hasKey("msgList")) {
            String msgTxt = stringRedisTemplate.opsForList().index("msgList", l);

            //只有当msgList 有新的消息的时候，才会获取消息
            if (msgTxt != null && msgTxt != "") {
//                list.remove(user);
                MessageEntity messageEntity = JSON.parseObject(msgTxt, MessageEntity.class);

                //消息坐标加-1
                stringRedisTemplate.opsForZSet().incrementScore("userList", user, -1);
                return messageEntity;
            }
        }
        return null;

    }
}
