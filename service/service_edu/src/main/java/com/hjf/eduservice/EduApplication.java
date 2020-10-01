package com.hjf.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Jiang锋时刻
 * @create 2020-09-30 7:51
 */
@SpringBootApplication
/**
 * 扫描指定路径下的包
 * 如果不添加注解, 启动器只能扫描当前项目中的类, 别的项目中的类扫描不到
 * 这样公共类中的配置也就使用不了了
 */
@ComponentScan(basePackages = {"com.hjf"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
