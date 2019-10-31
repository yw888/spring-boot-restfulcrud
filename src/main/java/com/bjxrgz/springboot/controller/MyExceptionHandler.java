package com.bjxrgz.springboot.controller;

import com.bjxrgz.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    //浏览器和客户端返回的都是json
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String, Object> handleException(Exception e){
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", "user.notexist");
//        map.put("message", e.getMessage());
//        return map;
//    }

    //达到了error自适应
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){

        Map<String, Object> map = new HashMap<>();
        //传入自己的状态码
//        request.setAttribute("javax.servlet.error.status_code", 400);
        map.put("code", "user.notexist");
        map.put("message", "用户出错啦");

        request.setAttribute("ext", map);
        //转发到/error
        return "forwrad:/error";
    }
}
