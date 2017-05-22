package com.anhoo.service.impl;

import com.anhoo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

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
    public Set<ZSetOperations.TypedTuple<String>> getVotes() {

        Set<ZSetOperations.TypedTuple<String>> set = stringRedisTemplate.opsForZSet().reverseRangeWithScores("vote", 0, -1);
        return set;
    }

    @Override
    public int addTickets(String tickets, String key) {
        if (!stringRedisTemplate.opsForSet().isMember("voteTickets", tickets)) {
            stringRedisTemplate.opsForSet().add("voteTickets", tickets);
            stringRedisTemplate.opsForZSet().incrementScore("vote", key, 1);
            return 1;
        } else {
            return 0;
        }
    }
}
