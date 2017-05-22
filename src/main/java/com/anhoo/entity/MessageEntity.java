package com.anhoo.entity;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/22
 * Time  16:48
 */

public class MessageEntity implements Serializable{

    private String user;
    private String content;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
