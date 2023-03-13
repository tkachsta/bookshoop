package com.example.MyBookShopApp.model.entities.Bookfile2Type;

import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "bookfile2type")
@Getter
@Setter
public class Bookfile2Type {
    @EmbeddedId
    private Bookfile2TypeKey bookfile2TypeKey;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bookfile", insertable = false, updatable = false)
    private BookFileEntity fileEntity;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_book_file_type", insertable = false, updatable = false)
    private BookFileType fileType;
    @ManyToOne
    @JoinColumn(name = "id_book", referencedColumnName = "id_book")
    private BookEntity bookEntity;
    @Column
    private String description;
    @Column
    private String hash;

}
