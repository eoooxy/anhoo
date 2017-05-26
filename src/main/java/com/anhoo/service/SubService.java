package com.anhoo.service;

import com.anhoo.entity.MessageEntity;
import org.springframework.data.redis.connection.Message;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/22
 * Time  11:26
 */

public interface SubService {

    void isCall(Message message);

    MessageEntity callBack(String user) throws InterruptedException;
}
