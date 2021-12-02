package com.guang.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guangyong.deng
 * @date 2021-11-25 17:21
 */
@RestController
//@RequestMapping("/ext/ds")
public class TestController {

    @GetMapping("/ext/login")
    public String login() {
        return "用户登录！";
    }
}
