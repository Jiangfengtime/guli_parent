package com.hjf.servicebase;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Jiang锋时刻
 * @create 2020-09-30 13:47
 */
@Configuration  // 声明配置类
@EnableSwagger2 // 启动Swagger注解
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                // 配置Swagger配置信息
                .apiInfo(webApiInfo())
                .select()
                // 不扫描/admin和/error下的内容
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    // 配置Swagger配置信息
    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("网站-课程中心API文档")
                .description("本文档描述了课程中心微服务接口定义")
                .version("1.0")
                .contact(new Contact("Helen", "http://www.jiangfengtime.top", "1344602850@qq.com"))
                .build();
    }
}
