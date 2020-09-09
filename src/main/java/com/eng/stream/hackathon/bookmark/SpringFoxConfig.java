package com.eng.stream.hackathon.bookmark;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .paths(PathSelectors.any())                          
          .apis(RequestHandlerSelectors.basePackage("com.eng.stream.hackathon.bookmark"))              
          .build().apiInfo(apiInfo());                                           
    }
    
    @Bean
    public ApiInfo apiInfo() {
        final ApiInfoBuilder builder = new ApiInfoBuilder();
        Contact apiContact=new Contact("Prabhu MURUGESAN", "", "pmurugesan2012@gmail.com");
        builder.title("Manage Bookmark services").version("1.0").license("(C) Copyright agil-mugil")
                .description("The API to manage bookmarks").contact(apiContact);
        return builder.build();
    }
}