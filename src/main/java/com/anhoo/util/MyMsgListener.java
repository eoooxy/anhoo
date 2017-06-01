package com.anhoo.util;

import com.anhoo.service.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * author XueYuan
 * data  2017/05/23 15:24
 */
public class MyMsgListener implements MessageListener {

    @Autowired
    SubService subService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void onMessage(Message message, byte[] bytes) {

        String channel = stringRedisTemplate.getStringSerializer().deserialize(message.getChannel()).split("_")[0];
        String content = stringRedisTemplate.getValueSerializer().deserialize(message.getBody()).toString();
        if (channel.equals("update")) {
            stringRedisTemplate.delete(content);
        } else {
            subService.isCall(message);
        }
//        System.out.println(SerializeUtil.unserialize(bytes).toString());
        System.out.println("当前的Message值为:" + message.toString());
    }

}
