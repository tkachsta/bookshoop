package com.example.MyBookShopApp.model.Book2Genre;
import com.example.MyBookShopApp.model.Book.BookEntity;
import com.example.MyBookShopApp.model.Genre.GenreEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Book2GenreKey implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genre", insertable = false, updatable = false)
    private GenreEntity genre;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_book", insertable = false, updatable = false)
    private BookEntity book;

}
