package com.example.MyBookShopApp.config;
import com.example.MyBookShopApp.model.components.collectioninit.BooksArchived;
import com.example.MyBookShopApp.model.components.collectioninit.BooksBought;
import com.example.MyBookShopApp.model.components.collectioninit.BooksInCart;
import com.example.MyBookShopApp.model.components.collectioninit.BooksPostponed;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/book-covers/**")
                .addResourceLocations("file:" + uploadPath + "/");
    }
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public BooksInCart booksInCart() {
        return new BooksInCart();
    }
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public BooksPostponed booksInPostponed() {
        return new BooksPostponed();
    }
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public BooksBought booksBought() {
        return new BooksBought();
    }
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public BooksArchived booksArchived() {
        return new BooksArchived();
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
