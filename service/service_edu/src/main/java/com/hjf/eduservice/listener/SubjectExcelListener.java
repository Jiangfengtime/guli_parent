package com.hjf.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjf.eduservice.entity.EduSubject;
import com.hjf.eduservice.entity.excel.SubjectData;
import com.hjf.eduservice.service.EduSubjectService;
import com.hjf.servicebase.exceptionhandler.GuliException;

/**
 * @author Jiang锋时刻
 * @create 2020-10-07 1:44
 */

// 因为SubjectExcelListener不能交给Spring进行管理, 需要自己new, 不能注入其他对象
// 需要通过有参构造手动注入
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    // 因为没有将该类交给Spring管理, 所以需要自己创建
    public EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }


    // 读取Excel内容, 一行一行读取
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new GuliException(20001, "文件数据为空");
        }
        // 一行一行读取, 第一个值为一级分类, 第二个值为二级分类
        // 先判断一级分类是否存在
        EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        // 一级分类添加
        if (existOneSubject == null) {
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
        }

        // 获取一级分类的id, 即为二级分类的父id
        String pid = existOneSubject.getId();

        // 再判断二级分类是否存在
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        // 二级分类添加
        if (existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    // 判断一级分类不能重复添加
    public EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        // 一级分类的parent_id = 0
        wrapper.eq("title", name).eq("parent_id", "0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    // 判断二级分类不能重复添加
    public EduSubject existTwoSubject(EduSubjectService subjectService, String name, String parentId) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name).eq("parent_id", parentId);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

}
