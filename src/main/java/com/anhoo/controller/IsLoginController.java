package com.anhoo.controller;

import com.anhoo.dto.ResultCode;
import com.anhoo.dto.ResultMsg;
import com.anhoo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author XueYuan
 * data  2017/05/03 20:48
 */
@Controller
@RequestMapping("/back")
public class IsLoginController {

    @RequestMapping("/isLogin")
    public ResultMsg isLogin(User user) {
        ResultMsg resultMsg = new ResultMsg();
        if (user != null) {
            resultMsg.setCode(ResultCode.SUCCESS);
            resultMsg.setMsg("Login success!!");
        } else {
            resultMsg.setCode(ResultCode.FAIL);
            resultMsg.setMsg("Login fail!!");
        }
//        modelMap.put("resultMsg", resultMsg);
        return resultMsg;
    }

}
