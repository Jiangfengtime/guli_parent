package com.hjf.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Jiang锋时刻
 * @create 2020-10-07 1:33
 */
@Data
@ApiModel("Excel实体类")
public class SubjectData {
    @ExcelProperty(index = 0)
    @ApiModelProperty("一级分类")
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    @ApiModelProperty("二级分类")
    private String twoSubjectName;
}
