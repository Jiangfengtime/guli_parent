package com.hjf.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Jiang锋时刻
 * @create 2020-09-30 7:52
 */

@Configuration
@MapperScan("com.hjf.eduservice.mapper")
public class EduConfig {
    // 逻辑删除
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }

    // 分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
