package com.anhoo.controller;

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
    public void isLogin(User user, ModelMap modelMap) {
        ResultMsg resultMsg = new ResultMsg();
        if (user != null) {
            resultMsg.setCode("100");
            resultMsg.setMsg("Login is success!!");
        } else {
            resultMsg.setCode("200");
            resultMsg.setMsg("Login is fail!!");
        }
        modelMap.put("resultMsg", resultMsg);
    }

}
