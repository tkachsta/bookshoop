package com.example.MyBookShopApp.model.Book;
import com.example.MyBookShopApp.model.Author.AuthorEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private Integer bookId;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer discount;
    @Column
    private String image;
    @Column(name = "is_bestseller", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isBestseller;
    @Column
    private String price;
    @Column
    private LocalDateTime pub_date;
    @Column
    private String slug;
    @Column
    private String title;
    @Column(name = "price_old")
    private String priceOld;

}
