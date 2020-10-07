package com.hjf.eduservice.controller;

import com.hjf.commonutils.ReturnType;
import io.swagger.annotations.ApiModel;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jiang锋时刻
 * @create 2020-10-04 13:50
 */

@ApiModel(description = "简易登录" )
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin  // 添加注解, 解决跨域问题
public class EduLoginController {
    // login
    @PostMapping("/login")
    public ReturnType login(){
        return ReturnType.ok().data("token", "admin");
    }

    // info
    @GetMapping("/info")
    public ReturnType info(){
        return ReturnType.ok().data("roles", "[admin]").data("name", "admin")
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
