package com.hjf.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author Jiang锋时刻
 * @create 2020-10-06 23:37
 */
public class TestReadExcel {
    // 方法1
    @Test
    public void test1(){
        String fileName = "F:\\data1.xlsx";
        EasyExcel.read(fileName, ReadData.class, new ExcelListener()).sheet("学生列表").doRead();
    }

    // 方法2
    @Test
    public void test2(){
        String fileName = "F:\\data1.xlsx";
        ExcelReader excelReader = null;
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(fileName));
            excelReader = EasyExcel.read(inputStream, ReadData.class, new ExcelListener()).build();
            ReadSheet readSheet = EasyExcel.readSheet("学生列表").build();
            excelReader.read(readSheet);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
            excelReader.finish();
        }
    }
}
