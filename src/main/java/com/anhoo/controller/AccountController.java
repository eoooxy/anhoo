package com.anhoo.controller;

import com.anhoo.dto.ResultCode;
import com.anhoo.dto.ResultMsg;
import com.anhoo.entity.UserEntity;
import com.anhoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author XueYuan
 * data  2017/05/03 20:48
 */
@Controller
@RequestMapping("/back")
public class AccountController {

    @Autowired
    UserService userServicel;

    @ResponseBody
    @RequestMapping("/isLogin")
    public ResultMsg isLogin(UserEntity userEntity) {
        ResultMsg resultMsg = new ResultMsg();
        if (userEntity.getUserName() != null && userEntity.getUserName() != ""
                && userEntity.getPassWord() != null && userEntity.getPassWord() != "") {
            if (userServicel.isLogin(userEntity) > 0) {
                resultMsg.setCode(ResultCode.SUCCESS);
                resultMsg.setMsg("登录成功");
            } else {
                resultMsg.setCode(ResultCode.FAIL);
                resultMsg.setMsg("登录失败");
            }
        } else {
            resultMsg.setCode(ResultCode.FAIL);
            resultMsg.setMsg("登录失败");
        }
        return resultMsg;
    }

    @ResponseBody
    @RequestMapping("/apply")
    public ResultMsg applyAccount(UserEntity userEntity) {
        ResultMsg resultMsg = new ResultMsg();
        if (userEntity.getUserName() != null && userEntity.getUserName() != ""
                && userEntity.getPassWord() != null && userEntity.getPassWord() != "") {

            //判断用户名是否已经存在了
            if (userServicel.isExists(userEntity) > 0) {
                resultMsg.setCode(ResultCode.FAIL);
                resultMsg.setMsg("当前帐号已存在！");
                return resultMsg;
            }
            //创建用户
            if (userServicel.create(userEntity) > 0) {
                resultMsg.setCode(ResultCode.SUCCESS);
                resultMsg.setMsg("注册成功！");
                return resultMsg;
            }
        } else {
            resultMsg.setCode(ResultCode.FAIL);
            resultMsg.setMsg("操作失败！");
        }
        return resultMsg;
    }

}
