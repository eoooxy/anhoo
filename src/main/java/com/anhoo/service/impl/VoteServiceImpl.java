package com.anhoo.service.impl;

import com.anhoo.entity.VoteEntity;
import com.anhoo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/19
 * Time  13:21
 */

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public List<VoteEntity> getVotes() {

        List<VoteEntity> lists = new ArrayList<>();
        Set<ZSetOperations.TypedTuple<String>> set = stringRedisTemplate.opsForZSet().rangeWithScores("vote", 0, -1);

        for (ZSetOperations.TypedTuple s : set) {
            VoteEntity voteEntity = new VoteEntity();
            voteEntity.setName(s.getValue().toString());
            voteEntity.setScores(s.getScore().intValue());
            lists.add(voteEntity);
        }
        return lists;

    }
}
