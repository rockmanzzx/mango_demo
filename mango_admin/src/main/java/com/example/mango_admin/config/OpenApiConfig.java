package com.example.mango_admin.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mango")
                        .version("1.0.0")
                        .description("描述"));
    }

    @Bean
    public OpenApiCustomiser globalHeaderOpenApiCustomiser() {
        return openApi -> {
            List<Parameter> globalParameters = new ArrayList<>();
            globalParameters.add(new Parameter().name("token")
                    .description("令牌")
                    .required(false)
                    .in("header")
                    .schema(new StringSchema()));
            openApi.getPaths().values().forEach(pathItem -> {
                pathItem.readOperations().forEach(operation -> {
                    operation.addParametersItem(globalParameters.get(0));
                });
            });
        };
    }
}