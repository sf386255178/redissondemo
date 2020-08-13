package com.bds.redissondemo.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author :Kevin Ding;
 * @TIME :2020/8/13;
 * @TODO :SwaggerConfig 接口文档配置类,直接访问ip+port/swagger-ui.html可见文档;
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //1.声明api 文档的属性 构建起
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("springboot使用在线文档构建RestFul风格Api")
                .description("Kevin Ding")
                .termsOfServiceUrl("######")
                .contact("java").version("1.0").build();
    }
    //2配置核心配置信息
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.bds.redissondemo.controller"))
                .paths(PathSelectors.any()).build();
    }
}
