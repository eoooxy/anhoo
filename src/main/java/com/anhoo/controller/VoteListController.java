package com.anhoo.controller;

import com.alibaba.fastjson.JSON;
import com.anhoo.dto.ResultCode;
import com.anhoo.dto.ResultMsg;
import com.anhoo.entity.VoteEntity;
import com.anhoo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/19
 * Time  11:26
 */

@Controller
public class VoteListController {

    @Autowired
    VoteService voteService;

    //    @ResponseBody
    @RequestMapping("/voteList")
    public String voteList(ModelMap modelMap) {

//        ResultMsg resultMsg = new ResultMsg();
//        resultMsg.setContent(JSON.toJSON(voteService.getVotes()));
//        resultMsg.setMsg("123");
//        resultMsg.setCode(ResultCode.SUCCESS);
//        return resultMsg;

        List<VoteEntity> votes = voteService.getVotes();
//        modelAndView.setViewName("vote");
//        modelAndView.addObject("votes", votes);
        modelMap.put("votes", votes);

        return "vote";
//        return modelAndView;
    }
}
