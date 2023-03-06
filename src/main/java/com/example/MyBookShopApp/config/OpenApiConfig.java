package com.example.MyBookShopApp.config;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class OpenApiConfig {
//
//    @Bean
//    public GroupedOpenApi publicApi() {
//        return GroupedOpenApi
//                .builder()
//                .group("bookshop-user")
//                .pathsToMatch("/")
//                .build();
//    }
//    @Bean
//    public OpenAPI springBookShopOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("BookShop API")
//                        .description("Bookshop sample pplication")
//                        .version("v0.0.1")
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
//                .externalDocs(new ExternalDocumentation()
//                        .description("Bookshop Wiki Documentation")
//                        .url("https://bookshop.wiki."))
//    }
//}
