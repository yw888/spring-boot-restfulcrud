package com.bjxrgz.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "aaaaaa".equals(password)){
            //登录成功，防止表单重复提交，采用重定向
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        }
        map.put("msg", "用户名或密码错误");
        return "login";
    }
}
