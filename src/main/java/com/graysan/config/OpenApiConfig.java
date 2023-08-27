package com.graysan.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPIMethod(){

        return new OpenAPI()
                .info(
                        new Info()
                                .description("blog tanımlama")
                                .version("v1")
                                .contact(new Contact().email("batuhanyalcinturk@gmail.com").url("url"))
                                .title("TitleBatu")
                                .termsOfService("Graysan INC")
                                .license(new License().url("https://www.xxx.com").name("name")));
    }
}
