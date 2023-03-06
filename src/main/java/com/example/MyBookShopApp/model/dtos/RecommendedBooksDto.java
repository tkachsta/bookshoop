package com.example.MyBookShopApp.model.dtos;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class RecommendedBooksDto {
    private final Integer count;
    private final List<BookEntity> books;

}
