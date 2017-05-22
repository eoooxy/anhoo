package com.anhoo.service.impl;

import com.anhoo.service.SubService;
import org.springframework.data.redis.connection.Message;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/22
 * Time  11:26
 */

public class SubServiceImpl implements SubService {


    Message message;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println(message.toString());
    }

    public Message getMessage() {
        return message;
    }
}
