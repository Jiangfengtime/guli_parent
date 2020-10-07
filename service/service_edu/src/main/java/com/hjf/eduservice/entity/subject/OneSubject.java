package com.hjf.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang锋时刻
 * @create 2020-10-07 13:06
 */

// 一级分类
@Data
public class OneSubject {
    private String id;
    private String title;

    // 一个一级分类里面有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
