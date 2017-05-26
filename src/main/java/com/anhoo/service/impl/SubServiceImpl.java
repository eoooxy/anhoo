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
//        stringRedisTemplate.opsForValue().set("broadcast", jedis.pubsubNumPat().toString());
//        jedis.close();

    }

    @Override
    public MessageEntity callBack(String user) throws InterruptedException {
//        String msgTxt = stringRedisTemplate.opsForList().index("msgList", -1);

//        new Thread(){
//
//        }.start();

//        List list = stringRedisTemplate.opsForList().range("msgList", 0, -1);
        while (true) {
            //模拟1s 查看一次 不至于一直在连接redis 低于1s的频率连接redis会报错
            Thread.sleep(1000);
            String msgTxt = stringRedisTemplate.opsForList().rightPop("msgList");
//            String msgTxt = "";
            if (msgTxt != null && msgTxt != ""/* && list.contains(user) && list.size() > 0*/) {
//                list.remove(user);
                MessageEntity messageEntity = JSON.parseObject(msgTxt, MessageEntity.class);
                return messageEntity;
            }
        }
    }
//        if (Long.parseLong(stringRedisTemplate.opsForValue().get("broadcast")) > 0) {
//            stringRedisTemplate.opsForValue().increment("broadcast", -1);
//
//            if (msgTxt != null && msgTxt != "") {
//                MessageEntity messageEntity = JSON.parseObject(msgTxt, MessageEntity.class);
//                return messageEntity;
//            }
//        } else {
//            stringRedisTemplate.opsForList().rightPop("msgList");
//        }

}
