package com.mrmilu.controller;

import com.mrmilu.pojo.Emp;
import com.mrmilu.pojo.Result;
import com.mrmilu.utils.XmlParserUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * 处理员工数据
 *
 */
@RestController
public class EmpController {
    @RequestMapping("/listEmp")
    public Result empList(){

        //获取存储数据的xml文件路径
        String file = "springboot-web-req-resp\\src\\main\\resources\\emp.xml";
        //调用XmlParserUtils中的方法来解析xml文件，并将数据封装成Emp类
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);

        empList.forEach(emp ->{
            //<!-- 1: 男, 2: 女 -->

            if (emp.getGender().equals("1")){
                emp.setGender("男");
            }else if (emp.getGender().equals("2")){
                emp.setGender("女");
            }
            //<!-- 1: 讲师, 2: 班主任 , 3: 就业指导 -->
            switch (emp.getJob()) {
                case "1" -> emp.setJob("讲师");
                case "2" -> emp.setJob("班主任");
                case "3" -> emp.setJob("就业指导");
            }
        });

        return Result.success(empList);
    }
}
