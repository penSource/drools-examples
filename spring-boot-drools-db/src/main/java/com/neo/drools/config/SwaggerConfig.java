/*
 * com.neo.drools.config.SwaggerConfig.java
 * Copyright 2018 Lifangyu, Inc. All rights reserved.
 * IYueDian PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.neo.drools.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Desc:TODO
 *
 * @author lifangyu
 * @date 2018/11/2.
 */
//@Conditional(SwaggerCondition.class)
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * /api.* [访问的路径匹配,如:SwaggerApiController.java @RequestMapping("/api/v1") 符合该路径匹配]
     *
     * @return
     */
    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.neo.drools.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 确保以下配置的信息可用，否则不能访问
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("drools规则引擎系统接口文档")
                .description("[规则引擎]系统接口文档")
                .license("Apache license")
                .version("2.0")
                .build();
    }
}