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
        String channel = "chat_";
        stringRedisTemplate.convertAndSend(channel + messageEntity.getUser(), messageEntity.getContent());
    }
}
