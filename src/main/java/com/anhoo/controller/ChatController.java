package com.anhoo.controller;

import com.anhoo.dto.ResultCode;
import com.anhoo.dto.ResultMsg;
import com.anhoo.entity.MessageEntity;
import com.anhoo.service.PubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by IntelliJ IDEA.
 * Author XueYuan
 * Data  2017/05/22
 * Time  16:54
 */

@Controller
@RequestMapping("/back")
public class ChatController {

    @Autowired
    PubService pubService;

    @ResponseBody
    @RequestMapping("/send")
    public ResultMsg sendMessage(MessageEntity messageEntity) {
        ResultMsg resultMsg = new ResultMsg();
        pubService.sendMessage(messageEntity);
        resultMsg.setMsg("发送成功！");
        resultMsg.setCode(ResultCode.SUCCESS);
        return resultMsg;
    }
}
