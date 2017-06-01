package com.anhoo.mapper;

import com.anhoo.entity.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/15
 * Time  15:42
 */

@Repository
public interface UserEntityMapper {

    int insert(UserEntity userEntity);

    UserEntity selectByUserName(String userName);

    int updateUser(UserEntity userEntity);
}
