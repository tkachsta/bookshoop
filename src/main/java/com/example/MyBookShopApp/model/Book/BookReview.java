package com.example.MyBookShopApp.model.Book;

import com.example.MyBookShopApp.model.Users.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "book_review")
public class BookReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bookreview")
    private Long bookreviewId;
    @Column(columnDefinition = "TEXT")
    private String text;
    @Column
    private Timestamp time;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_book", referencedColumnName = "id_book")
    private BookEntity book;
}
