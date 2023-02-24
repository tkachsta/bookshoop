package com.example.MyBookShopApp.model.Book2Author;
import com.example.MyBookShopApp.model.Author.AuthorEntity;
import com.example.MyBookShopApp.model.Book.BookEntity;
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
public class Book2AuthorKey implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_author", insertable = false, updatable = false)
    private AuthorEntity author;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_book", insertable = false, updatable = false)
    private BookEntity book;

}
