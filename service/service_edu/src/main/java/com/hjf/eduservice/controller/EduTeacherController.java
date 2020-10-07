package com.hjf.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjf.commonutils.ReturnType;
import com.hjf.eduservice.entity.EduTeacher;
import com.hjf.eduservice.entity.vo.TeacherQuery;
import com.hjf.eduservice.service.EduTeacherService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Jiang锋时刻
 * @since 2020-09-30
 */
@ApiModel(description = "讲师管理")      // 在类上加注解
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    // 查询讲师表中的所有数据
    // rest风格
    @GetMapping("/findAll")
    @ApiOperation(value = "所有讲师列表")
    public ReturnType findAllTeacher(){
        // 调用Service方法, 实现查询所有的操作
        List<EduTeacher> list = teacherService.list(null);
        // 链式编程
        return ReturnType.ok().data("items", list);
    }


    // id 需要通过路径传
    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "逻辑删除讲师")
    // @ApiParam(name = "id", value = "讲师id", required = true) 在参数上加注解
    public ReturnType removeTeacher( @ApiParam(name = "id", value = "讲师id", required = true) @PathVariable("id") String id){
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return ReturnType.ok();
        } else {
            return ReturnType.error();
        }
    }


    // 分页查询讲师信息
    // current: 当前页; limit: 每页记录数
    @GetMapping("/pageTeacher/{current}/{limit}")
    @ApiOperation(value = "分页查询讲师")
    public ReturnType pageTeacher(
            @ApiParam(name = "current", value = "当前页", required = true) @PathVariable Integer current,
            @ApiParam(name = "limit", value = "页面大小", required = true) @PathVariable Integer limit){
        // 1. 创建一个page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);


//        try {
//            int i = 10 / 0;
//        } catch (Exception e) {
//            // 执行自定义异常
//            throw new GuliException(ResultCode.ERROR, "执行了自定义异常处理");
//        }


        // 2. 调用方法实现分页
        //    调用方法时候, 底层封装, 把分页所有数据封装到pageTeacher对象里面
        teacherService.page(pageTeacher, null);

        long total = pageTeacher.getTotal();    // 总记录数
        List<EduTeacher> records = pageTeacher.getRecords();    // 数据list集合

//        Map<String, Object> map = new HashMap<>();
//        map.put("total", total);
//        map.put("rows", records);
//        return ReturnType.ok().data(map);
        return ReturnType.ok().data("total", total).data("rows", records);
    }

    // 条件查询带分页的方法
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    @ApiOperation(value = "条件查询讲师(带分页)")
    public ReturnType pageTeacherCondition(
            @ApiParam(value = "当前页", required = true) @PathVariable Integer current,
            @ApiParam(value = "页面大小", required = true) @PathVariable Integer limit,
            // @RequestBody: 使用json传递数据, 把json数据封装到对应的对象里
            // 此时必须使用post方式提交, 否则获取不到提交的信息
            @ApiParam(value = "查询条件类") @RequestBody(required = false) TeacherQuery teacherQuery){

        // 创建Page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        // 构建条件, 多条件组合查询
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        // 判断条件值是否为空, 如果不为空拼接条件
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            // 构建条件
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            // 这里的字段时表总的字段名, 而不是类总的属性名
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            // 这里的字段时表总的字段名, 而不是类总的属性名
            wrapper.le("gmt_create", end);
        }

        // 排序
        wrapper.orderByDesc("gmt_create");


        // 调用方法实现条件查询分页
        teacherService.page(pageTeacher, wrapper);

        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return ReturnType.ok().data("total", total).data("rows", records);
    }

    // 添加讲师接口的方法
    @PostMapping("/addTeacher")
    @ApiOperation(value = "添加讲师")
    public ReturnType addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if (save){
            return ReturnType.ok();
        } else {
            return ReturnType.error();
        }
    }


    // 根据讲师id进行查询
    @GetMapping("/getTeacher/{id}")
    @ApiOperation(value = "根据讲师id查询")
    public ReturnType getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return ReturnType.ok().data("teacher", eduTeacher);
    }

    // 讲师修改功能
    // json数据中必须包含id
    @PostMapping("/updateTeacher")
    @ApiOperation(value = "讲师修改")
    public ReturnType updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean update = teacherService.updateById(eduTeacher);
        if (update) {
            return ReturnType.ok();
        } else {
            return ReturnType.error();
        }
    }

}

