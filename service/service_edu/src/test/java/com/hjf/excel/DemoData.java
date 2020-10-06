package com.hjf.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Jiang锋时刻
 * @create 2020-10-06 22:34
 */

@Data
public class DemoData {
    // 设置excel表头名称
    @ExcelProperty("学生编号")
    private Integer sno;
    @ExcelProperty("学生姓名")
    private String name;
}
