package com.example.MyBookShopApp.model.entities.Misc;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    private String hash;
    @Column
    private String path;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "boook_file_type", referencedColumnName = "id_book_file_type")
    private BookFileType bookFileType;
}
