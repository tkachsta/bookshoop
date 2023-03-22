package com.example.MyBookShopApp.model.dtos;
import com.example.MyBookShopApp.model.entities.Book.AuthorsStringSerializer;
import com.example.MyBookShopApp.model.entities.Book2Author.Book2Author;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class BookDto {
    private String description;
    private String discount;
    private String image;
    private boolean bestseller;
    private Integer price;
    private LocalDateTime pub_date;
    private String slug;
    private String title;
    private Integer priceOld;
    private String status;
    @JsonSerialize(using = AuthorsStringSerializer.class)
    Set<Book2Author> authors;

}
