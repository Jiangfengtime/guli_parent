package com.hjf.servicebase.exceptionhandler;

import com.hjf.commonutils.ReturnType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jiang锋时刻
 * @create 2020-10-01 10:04
 */
// 统一异常处理类
// 优先找特定异常, 最后再全局异常
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // 指定出现扫描异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody      // 为了能返回数据
    public ReturnType error(Exception e){
        e.printStackTrace();
        return ReturnType.error().message("执行Exception异常处理...");
    }

    // 特定异常
    // 指定出现扫描异常执行这个方法
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody      // 为了能返回数据
    public ReturnType error(ArithmeticException e){
        e.printStackTrace();
        return ReturnType.error().message("执行ArithmeticException异常处理...");
    }

    // 自定义异常
    @ExceptionHandler(GuliException.class)
    @ResponseBody      // 为了能返回数据
    public ReturnType error(GuliException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return ReturnType.error().code(e.getCode()).message(e.getMsg());
    }
}
