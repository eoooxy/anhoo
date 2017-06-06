package com.anhoo.controller;

import com.anhoo.dto.ResultCode;
import com.anhoo.dto.ResultMsg;
import com.anhoo.entity.MessageEntity;
import com.anhoo.service.ChatService;
import com.anhoo.service.PubService;
import com.anhoo.service.SubService;
import com.anhoo.util.SerializeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
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
    @Autowired
    SubService subService;
    @Autowired
    ChatService chatService;

    @ResponseBody
    @RequestMapping("/send")
    public ResultMsg sendMessage(MessageEntity messageEntity) {
        ResultMsg resultMsg = new ResultMsg();
        if (messageEntity != null && !messageEntity.getUser().equals("") && !messageEntity.getContent().equals("")) {
            pubService.sendMessage(messageEntity);
            resultMsg.setMsg("发送成功！");
            resultMsg.setCode(ResultCode.SUCCESS);
            return resultMsg;
        }
        resultMsg.setMsg("输入信息有误！");
        resultMsg.setCode(ResultCode.FAIL);
        return resultMsg;
    }

    @ResponseBody
    @RequestMapping("/callBack")
    public ResultMsg callMsg(String user) throws InterruptedException {
        ResultMsg resultMsg = new ResultMsg();
        Logger logger = LogManager.getLogger(ChatController.class);
        MessageEntity message;
        message = subService.callBack(user);
        if (message != null) {
            resultMsg.setCode(ResultCode.SUCCESS);
            resultMsg.setContent(message);
            return resultMsg;
        } else {
            resultMsg.setCode(ResultCode.FAIL);
            return resultMsg;
        }
    }

    @ResponseBody
    @RequestMapping("/join")
    public ResultMsg addChatUser(String user) {
        ResultMsg resultMsg = new ResultMsg();
        if (chatService.addUser(user) > 0) {
            resultMsg.setCode(ResultCode.SUCCESS);
        } else {
            resultMsg.setCode(ResultCode.FAIL);
            resultMsg.setMsg("昵称已经存在，请重新输入！");
        }
        return resultMsg;
    }
}
