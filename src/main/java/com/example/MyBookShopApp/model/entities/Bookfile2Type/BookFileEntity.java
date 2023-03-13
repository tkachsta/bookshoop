package com.example.MyBookShopApp.model.entities.Bookfile2Type;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "book_file")
public class BookFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bookfile")
    private Long bookfileId;
    @Column
    private String path;



}
