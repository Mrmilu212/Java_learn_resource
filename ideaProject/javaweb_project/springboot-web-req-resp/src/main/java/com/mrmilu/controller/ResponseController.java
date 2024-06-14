package com.mrmilu.controller;

import com.mrmilu.pojo.Address;
import com.mrmilu.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试响应数据
 */
@RestController
public class ResponseController {
    @RequestMapping("/hello")
    public Result hello(){
        System.out.println("Hello World ~");
        return Result.success("Hello World ~");
    }

    @RequestMapping("/getAdr")
    public Result getAdr(){
        Address address = new Address("广东", "肇庆");
        return Result.success(address);
    }

    @RequestMapping("/getListAdr")
    public Result getListAdr(){
        List<Address> adrs = new ArrayList<>();

        adrs.add(new Address("广东", "肇庆"));
        adrs.add(new Address("广东", "广州"));

        return Result.success(adrs);
    }
}
