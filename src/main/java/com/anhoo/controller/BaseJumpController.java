package com.anhoo.controller;

import com.alibaba.fastjson.JSON;
import com.anhoo.dto.ResultCode;
import com.anhoo.dto.ResultMsg;
import com.anhoo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

/**
 * author XueYuan
 * data  2017/05/03 20:50
 */
@Controller
public class BaseJumpController {

    @Autowired
    VoteService voteService;

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("vote")
    public String vote(ModelMap modelMap) {
        Set set = voteService.getVotes();
        modelMap.put("votes", JSON.toJSON(set));
        return "vote";
    }
}
