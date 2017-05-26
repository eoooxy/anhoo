package com.anhoo.service.impl;

import com.anhoo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * author XueYuan
 * data  2017/05/24 15:50
 */

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Override
    public int addUser(String user) {
        if (stringRedisTemplate.opsForSet().isMember("userList", user)) {
            return 0;
        } else {
            stringRedisTemplate.opsForSet().add("userList", user);
            return 1;
        }
    }
}
