package com.womeng.excHandler;

import com.womeng.entity.Result;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 捕获所有的异常
    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex) {
        ex.printStackTrace();
        return Result.error("对不起，操作失败，请联系管理员");
    }

    // 捕获 NoResourceFoundException 异常
    @ExceptionHandler(NoResourceFoundException.class)
    public Result noResourceFoundException(NoResourceFoundException ex) {
        ex.printStackTrace();
        return Result.error("资源未找到");
    }

    // 捕获 HttpRequestMethodNotSupportedException 异常
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        ex.printStackTrace();
        return Result.error("请求动词错误");
    }

}
