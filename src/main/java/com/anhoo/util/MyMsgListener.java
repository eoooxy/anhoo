package com.anhoo.util;

import com.anhoo.service.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * author XueYuan
 * data  2017/05/23 15:24
 */
public class MyMsgListener implements MessageListener {

    @Autowired
    SubService subService;


    @Override
    public void onMessage(Message message, byte[] bytes) {
        subService.isCall(message);
//        System.out.println(SerializeUtil.unserialize(bytes).toString());
        System.out.println(message.toString());
    }

}
