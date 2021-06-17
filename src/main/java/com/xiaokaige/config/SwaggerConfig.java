package com.xiaokaige.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 启动Swagger2配置
 *
 * @author lgs
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket customDocket() {

        // locale
        ParameterBuilder locale = new ParameterBuilder();
        locale.name("locale").description("国际化请求头。\n简体中文：zh_CN;\n美式英语：en_US;\n马来西亚语：ms_MY;\n泰国语：th_TH;\n繁体中文：zh_TW;\n越南语：vi_VN;\n新加坡华语：zh_SG")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .hidden(false)
                .required(false)
                .defaultValue("zh_CN")
                .build();

        // swagger
        ParameterBuilder swagger = new ParameterBuilder();
        swagger.name("swagger").description("swagger标识")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .hidden(false)
                .required(false)
                .defaultValue("1")
                .build();

        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(locale.build());
        parameterList.add(swagger.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xiaokaige.controller"))
                .build()
                .globalOperationParameters(parameterList);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //文档说明
                .title("测试APP API接口文档")
                //文档版本说明
                .version("V1.0.0")
                .build();
    }
}