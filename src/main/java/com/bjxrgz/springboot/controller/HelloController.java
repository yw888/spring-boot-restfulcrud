package com.bjxrgz.springboot.controller;

import com.bjxrgz.springboot.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user){
        if(!user.equals("aaa")){
            throw new UserNotExistException();
        }
        return "hello word";
    }

    @RequestMapping("/success")
    public String success(){
        return "success";
    }

//    @RequestMapping({"/","/index.html"})
//    public String index(){
//        return "index";
//    }
}
