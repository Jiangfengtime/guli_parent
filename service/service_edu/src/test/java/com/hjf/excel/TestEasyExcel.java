package com.hjf.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang锋时刻
 * @create 2020-10-06 22:37
 */
public class TestEasyExcel {
    // 方式1
    @Test
    public void test01() {
        // 实现Excel写的操作
        // 1. 设置写入文件夹地址和excel文件名称
        String fileName = "F:\\data1.xlsx";

        // 2. 调用easyExcel里面的方法实现写操作
        EasyExcel
                // write()的两个参数, 第一个参数是文件路径名称, 第二个参数是实体类class
                .write(fileName, DemoData.class)
                // 指定excel表中sheet的名称
                .sheet("学生列表")
                .doWrite(getData());
    }

    // 方式2: 需要手动关闭流
    @Test
    public void test02() {
        String fileName = "F:\\data2.xlsx";
        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("学生列表").build();
        excelWriter.write(getData(), writeSheet);
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }

    // 创建方法, 返回list集合
    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setName("学生" + i);
            list.add(data);
        }
        return list;
    }
}
