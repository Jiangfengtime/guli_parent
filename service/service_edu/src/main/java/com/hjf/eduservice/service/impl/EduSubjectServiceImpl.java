package com.hjf.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjf.eduservice.entity.EduSubject;
import com.hjf.eduservice.entity.excel.SubjectData;
import com.hjf.eduservice.entity.subject.OneSubject;
import com.hjf.eduservice.entity.subject.TwoSubject;
import com.hjf.eduservice.listener.SubjectExcelListener;
import com.hjf.eduservice.mapper.EduSubjectMapper;
import com.hjf.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Jiang锋时刻
 * @since 2020-10-07
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    // 添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            // 文件输入流
            InputStream in = file.getInputStream();
            // 调用方法进行读取
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllSubject() {
        // 1. 查询所有一级分类(parentId == 0)
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");

        // 方式1: service中调用mapper方法, 可以直接调用baseMapper
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);
        // 方法2: this方法也可以调用
        // List<EduSubject> oneSubjectList = this.list(wrapperOne);

        // 2. 查询所有二级分类(parentId != 0)
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        // 创建list集合, 用于存放最终封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        // 3. 封装一级分类
        // 查询出所有的一级分类list集合, 遍历得到每一个一级分类对象,
        // 获得每个一级分类对象值, 封装到要求的finalSubjectList集合中
        for (EduSubject eduSubject: oneSubjectList){
            // 1. 把eduSubject里面的值获取出来, 放到OneSubject对象中
            OneSubject oneSubject = new OneSubject();
            // 方式1
            // oneSubject.setId(eduSubject.getId());
            // oneSubject.setTitle(eduSubject.getTitle());

            // 方式2: 使用工具类
            // org.springframework.beans.BeanUtils
            // copyProperties: 将一个对象中的值, 放到另一个对象中
            // 将eduSubject中的值, set/get到oneSubject中
            BeanUtils.copyProperties(eduSubject, oneSubject);

            // 2. 多个OneSubject放到finalSubjectList集合中
            finalSubjectList.add(oneSubject);

            // 4. 封装二级分类
            // 在一级分类循环遍历查询所有的二级分类
            // 创建list集合封装每个一级分类的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            // 遍历二级分类list集合
            for (EduSubject tSubject: twoSubjectList){
                // 判断二级分类的parentId和一级分类的id是否相等
                if (tSubject.getParentId().equals(eduSubject.getId())){
                    // 把tSubject值复制到TwoSubject里面, 放到twoFinalSubjectList里面
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject, twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            // 把一级分类下面的二级分类下的二级分类, 放到一级分类中
            oneSubject.setChildren(twoFinalSubjectList);
        }
        return finalSubjectList;
    }
}
