package com.leandrosouza.leandroagrotis.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Locale;

@Configuration
public class ConfigBeans {

    public static final String BASE_PACKAGE_SWAGGER = "com.leandrosouza.leandroagrotis.api";

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setCacheSeconds(300);
        return messageSource;
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Agrotis - Leandro Souza")
                .description("API para processo seletivo")
                .version("1.0")
                .build();
    }

    @Bean
    public Locale locale() {
        return new Locale("pt", "BR");
    }

    @Bean
    public Docket myApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE_SWAGGER))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
}
