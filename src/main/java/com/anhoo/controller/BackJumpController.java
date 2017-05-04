package com.anhoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author XueYuan
 * data  2017/05/03 20:50
 */

@Controller
@RequestMapping("back")
public class BackJumpController {

    @RequestMapping("/login")
    public String login() {
        return "/back/login";
    }


    @RequestMapping("/index")
    public String index() {
        return "/back/index";
    }


}
