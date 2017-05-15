package com.anhoo.entity;

import java.io.Serializable;

/**
 * author XueYuan
 * data  2017/05/03 20:48
 */
public class UserEntity implements Serializable {

    private int recId;

    private String userName;

    private String passWord;

    public int getRecid() {
        return recId;
    }

    public void setRecid(int recId) {
        this.recId = recId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
