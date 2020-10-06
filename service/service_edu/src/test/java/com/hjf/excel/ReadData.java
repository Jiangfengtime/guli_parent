package com.hjf.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Jiang锋时刻
 * @create 2020-10-06 23:20
 */
@Data
public class ReadData {
    // 设置列对应的属性
    @ExcelProperty(index = 0)
    private int sid;

    @ExcelProperty(index = 1)
    private String sname;
}
