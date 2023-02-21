package com.example.MyBookShopApp.data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private Integer id;
    private Author author;
    private String title;
    private String priceOld;
    private String price;
    private Integer discount;
    private boolean isBestseller;

}
