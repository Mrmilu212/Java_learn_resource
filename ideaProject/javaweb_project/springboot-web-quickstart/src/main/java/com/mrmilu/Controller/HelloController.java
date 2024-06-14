package com.mrmilu.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//请求处理类 --需要用@RestController标识
@RestController
public class HelloController {
    //请求处理方法--需要用@RequestMapping()绑定请求地址
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("Hello World");
        return "Hello World";
    }
}
