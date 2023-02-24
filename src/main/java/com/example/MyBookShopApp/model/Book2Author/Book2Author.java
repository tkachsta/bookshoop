package com.example.MyBookShopApp.model.Book2Author;
import com.example.MyBookShopApp.model.Author.AuthorEntity;
import com.example.MyBookShopApp.model.Book.BookEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "book2author")
public class Book2Author {
    @EmbeddedId
    private Book2AuthorKey book2AuthorKey;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_author", insertable = false, updatable = false)
    private AuthorEntity author;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_book", insertable = false, updatable = false)
    private BookEntity book;
    @Column(name = "sort_index")
    private Integer sortIndex;

}
