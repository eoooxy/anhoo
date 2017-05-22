package com.anhoo.controller;

import com.alibaba.fastjson.JSON;
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
@RequestMapping("/back")
public class BackJumpController {

    @Autowired
    VoteService voteService;

    @RequestMapping("/login")
    public String login() {
        return "/back/login";
    }

    @RequestMapping("/index")
    public String index() {
        return "/back/index";
    }

    @RequestMapping("/chat")
    public String chat() {
        return "/back/chat";
    }

    @RequestMapping("/vote")
    public String vote(ModelMap modelMap) {
        Set set = voteService.getVotes();
        modelMap.put("votes", JSON.toJSON(set));
        return "/back/vote";
    }

}
