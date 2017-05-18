package com.anhoo.service.impl;

import com.alibaba.fastjson.JSON;
import com.anhoo.entity.UserEntity;
import com.anhoo.mapper.UserEntityMapper;
import com.anhoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/15
 * Time  10:59
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    UserEntityMapper userEntityMapper;

    @Override
    public int isLogin(UserEntity userEntity) {
        Map<Object, Object> maps = stringRedisTemplate.opsForHash().entries("User");

        UserEntity u = userEntityMapper.selectByUserName(userEntity.getUserName());

        if (maps.get(userEntity.getUserName()) != null) {
            //把json转成对应的Object对象
            UserEntity entity = JSON.parseObject(maps.get(userEntity.getUserName()).toString(), UserEntity.class);
            if (entity.getPassWord().equals(userEntity.getPassWord())) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public int create(UserEntity userEntity) {
        //协议把对象转成 json 存储到redis中
        stringRedisTemplate.opsForHash().put("User", userEntity.getUserName(), JSON.toJSONString(userEntity));
        return 1;
    }

    @Override
    public int isExists(UserEntity userEntity) {
        if (stringRedisTemplate.opsForHash().hasKey("User", userEntity.getUserName())) {
            return 1;
        } else {
            return 0;
        }
    }

}
