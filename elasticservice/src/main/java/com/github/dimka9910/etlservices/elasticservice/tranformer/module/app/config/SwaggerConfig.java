package com.github.dimka9910.etlservices.elasticservice.tranformer.module.app.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springDemoOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("service API")
                        .description("Service API")
                        .version("v0.0.1")
                        .license(new License().name("My").url("http://github.com/dimka9910")))
                .externalDocs(new ExternalDocumentation()
                        .description("Service Documentation")
                        .url("http://github.com/dimka9910"));
    }

    @Bean
    public GroupedOpenApi elasticServiceDocApi() {
        return GroupedOpenApi.builder()
                .group("elastic-api")
                .pathsToMatch("/rest/**")
                .build();
    }


}
