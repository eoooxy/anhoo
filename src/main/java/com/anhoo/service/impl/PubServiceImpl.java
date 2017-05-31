package com.anhoo.service.impl;

import com.anhoo.entity.MessageEntity;
import com.anhoo.service.PubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/22
 * Time  16:45
 */

@Service
public class PubServiceImpl implements PubService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void sendMessage(MessageEntity messageEntity) {
        //消息的频道为chat_*
        String channel = "chat_";
        String content = messageEntity.getContent();
        //使得发送消息的 频道为chat_用户名  例如chat_jack 为了后面能根据这个得到 jack用户
        stringRedisTemplate.convertAndSend(channel + messageEntity.getUser(), content);
    }
}
