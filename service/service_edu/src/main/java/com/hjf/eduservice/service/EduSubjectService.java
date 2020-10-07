package com.hjf.eduservice.service;

import com.hjf.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjf.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Jiang锋时刻
 * @since 2020-10-07
 */
public interface EduSubjectService extends IService<EduSubject> {

    // 添加课程分类
    public void saveSubject(MultipartFile file, EduSubjectService subjectService);

    // 课程分类列表(树形)
    public List<OneSubject> getAllSubject();
}
