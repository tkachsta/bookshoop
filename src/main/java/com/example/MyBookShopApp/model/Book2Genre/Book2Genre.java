package com.example.MyBookShopApp.model.Book2Genre;
import com.example.MyBookShopApp.model.Book.BookEntity;
import com.example.MyBookShopApp.model.Genre.GenreEntity;

import javax.persistence.*;

@Entity
@Table
public class Book2Genre {
    @EmbeddedId
    private Book2GenreKey book2GenreKey;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genre", insertable = false, updatable = false)
    private GenreEntity genre;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_book", insertable = false, updatable = false)
    private BookEntity book;

}
