package com.study.blog.springboot.config;

import com.study.blog.springboot.constant.OpenApiConstant;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = OpenApiConstant.SECURITY_NAME,
        type = SecuritySchemeType.HTTP,
        bearerFormat = OpenApiConstant.SECURITY_BEARER_FORMAT,
        scheme = OpenApiConstant.SECURITY_SCHEME
)
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String springdocVersion) {
        Info info = new Info()
                .title("Blog API")
                .version(springdocVersion)
                .description("Spring Boot 를 활용한 Blog API 입니다.");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

    @Bean
    public GroupedOpenApi adminAPI() {
        return GroupedOpenApi.builder().group("1. 관리자 API").pathsToMatch("/api/admin/**").build();
    }

    @Bean
    public GroupedOpenApi userAPI() {
        return GroupedOpenApi.builder().group("2. 사용자 API").pathsToMatch("/api/v1/**").build();
    }
}
