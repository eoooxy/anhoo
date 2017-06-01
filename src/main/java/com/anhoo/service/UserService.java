package com.anhoo.service;

import com.anhoo.entity.UserEntity;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/15
 * Time  10:57
 */

public interface UserService {

    int isLogin(UserEntity userEntity);

    int create(UserEntity userEntity);

    int isExists(UserEntity userEntity);

    int updata(UserEntity userEntity);
}
