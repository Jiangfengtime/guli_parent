package com.hjf.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.hjf.service.OssService;
import com.hjf.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author Jiang锋时刻
 * @create 2020-10-05 23:06
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // 工具类获取值
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 获取上传文件输入流。
            InputStream inputStream = file.getInputStream();

            // 获取文件名称
            String fileName = file.getOriginalFilename();

            // 1. 在文件名称里面添加随机唯一的的值
            String uuid = UUID.randomUUID().toString().replace("-", "");

            // 2. 把文件按照日期进行分类
            // 获取当前日期: 2020/10/06
            String datePath = new DateTime().toString("yyyy/MM/dd");
            // 拼接
            fileName = datePath + "/" + uuid + fileName;
            /**
             * 调用oss方法实现上传
             *  1. 第一个参数: Bucket名称
             *  2. 第二个参数: 上传到oss文件路径和文件名称
             *  3. 第三个参数: 上传文件输入流
             */
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            // 把上传之后的文件路径返回
            // 需要把上传到阿里云OSS路径手动拼接起来
            // https://guli-avatar-warehouse.oss-cn-hangzhou.aliyuncs.com/01.png
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
