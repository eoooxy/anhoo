package com.anhoo.service;

import com.anhoo.entity.VoteEntity;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/19
 * Time  13:21
 */

public interface VoteService {

    List<VoteEntity> getVotes();
}
