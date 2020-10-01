package com.hjf.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Jiang锋时刻
 * @create 2020-09-30 23:49
 */
@Data
@ApiModel(value = "Teacher查询对象", description = "讲师查询对象封装")
public class TeacherQuery implements Serializable {
    @ApiModelProperty(value = "教师名称, 模糊查询")
    private String name;
    @ApiModelProperty(value = "头衔, 1: 高级讲师; 2: 首席讲师")
    private Integer level;
    @ApiModelProperty(value = "查询开始事件" , example = "2020-01-01 01:32:21")
    private String begin;   // 这里使用String类型, 前端传过来的数据无需进行类型转换
    @ApiModelProperty(value = "查询结束事件" , example = "2020-03-01 01:32:21")
    private String end;
}
