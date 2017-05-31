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
        //判断userList 已经其中的用户是否已经存在
        if (stringRedisTemplate.hasKey("userList") && stringRedisTemplate.opsForZSet().score("userList", user) != null) {
            return 0;
        } else {
            //增加新的用户，但是要判断下，是否是第一次刚启动的时候
            int currentInidex;
            if (stringRedisTemplate.hasKey("msgList")) {
                currentInidex = (int) (-1 - stringRedisTemplate.opsForList().size("msgList"));
            } else {
                currentInidex = -1;
            }
            stringRedisTemplate.opsForZSet().add("userList", user, currentInidex);
            return 1;
        }
    }
}
