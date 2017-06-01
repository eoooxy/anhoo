package com.anhoo.service.impl;

import com.alibaba.fastjson.JSON;
import com.anhoo.dto.RedisKey;
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
//        Map<Object, Object> maps = stringRedisTemplate.opsForHash().entries("User");

        String s = stringRedisTemplate.opsForValue().get(RedisKey.INFOKEY);
        UserEntity u = userEntityMapper.selectByUserName(userEntity.getUserName());
//
//        if(u.getPassWord().equals(userEntity.getPassWord()) && u.getUserName().equals(userEntity.getUserName())){
//
//        }

        if (s != null) {
            //把json转成对应的Object对象
            UserEntity entity = JSON.parseObject(s, UserEntity.class);
            if (entity.getPassWord().equals(userEntity.getPassWord())) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (userEntity.getPassWord().equals(u.getPassWord()) && userEntity.getUserName().equals(u.getUserName())) {
                stringRedisTemplate.opsForValue().set(RedisKey.INFOKEY, JSON.toJSONString(u));
                return 1;
            } else {
                return 0;
            }
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

    @Override
    public int updata(UserEntity userEntity) {
        String channel = "update_";
        if (userEntityMapper.updateUser(userEntity) > 0) {
            stringRedisTemplate.convertAndSend(channel + userEntity.getUserName(), RedisKey.INFOKEY);
            return 1;
        }
        return 0;
    }


}
