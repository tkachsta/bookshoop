package com.example.MyBookShopApp.model.Misc;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "book_file_type")
public class BookFileType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book_file_type")
    private Long bookFileTypeId;
    @Column
    private String description;
    @Column
    private String name;
}
