package com.anhoo.service;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/19
 * Time  13:21
 */

public interface VoteService {

    Set<ZSetOperations.TypedTuple<String>> getVotes();

    int addTickets(String tickets,String key);
}
