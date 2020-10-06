package com.hjf.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jiang锋时刻
 * @create 2020-10-05 23:05
 */
public interface OssService {
    // 上传头像到OSS
    public String uploadFileAvatar(MultipartFile file);
}
