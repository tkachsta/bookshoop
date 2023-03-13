package com.example.MyBookShopApp.model.entities.Bookfile2Type;
import com.example.MyBookShopApp.model.entities.Bookfile2Type.BookFileEntity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

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
    private String name;

}
