package com.anhoo.controller;

import com.anhoo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author XueYuan
 * data  2017/05/03 20:50
 */
@Controller
public class BaseJumpController {

    @RequestMapping("index")
    public String index() {
        return "index";
    }

}
