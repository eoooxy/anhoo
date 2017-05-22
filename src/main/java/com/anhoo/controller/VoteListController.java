package com.anhoo.controller;

import com.anhoo.dto.ResultCode;
import com.anhoo.dto.ResultMsg;
import com.anhoo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @ResponseBody
    @RequestMapping("/add")
    public ResultMsg addTicket(String inviteCode, String key) {
        ResultMsg resultMsg = new ResultMsg();
        if (voteService.addTickets(inviteCode, key) > 0) {
            resultMsg.setCode(ResultCode.SUCCESS);
            resultMsg.setMsg("投票成功，3秒后刷新页面！");
        } else {
            resultMsg.setCode(ResultCode.FAIL);
            resultMsg.setMsg("当前票号已投过票！");
        }
        return resultMsg;
    }


}
