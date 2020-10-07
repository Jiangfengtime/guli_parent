package com.hjf.eduservice.controller;


import com.hjf.commonutils.ReturnType;
import com.hjf.eduservice.entity.subject.OneSubject;
import com.hjf.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Jiang锋时刻
 * @since 2020-10-07
 */
@ApiModel(description = "课程分类管理")
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    // 添加课程分类
    // 获取上传过来文件, 把文件内容读取出来
    @PostMapping("addSubject")
    public ReturnType addSubject(MultipartFile file){
        // 上传excel
        subjectService.saveSubject(file, subjectService);
        return ReturnType.ok();
    }

    // 课程分类列表(树形)
    @GetMapping("getAllSubject")
    public ReturnType getAllSubject(){
        // list集合泛型是一级分类, 因为一级分类中包含了二级分类
        List<OneSubject> list = subjectService.getAllSubject();
        return ReturnType.ok().data("list", list);
    }

}

