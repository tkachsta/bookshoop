package com.example.MyBookShopApp.model.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter

public class BooksListDto {
    private final Integer count;
    private final List<BookDto> books;

}
