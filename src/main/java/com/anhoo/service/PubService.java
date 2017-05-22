package com.anhoo.service;

import com.anhoo.entity.MessageEntity;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/22
 * Time  16:45
 */

public interface PubService {
    void sendMessage(MessageEntity messageEntity);
}
