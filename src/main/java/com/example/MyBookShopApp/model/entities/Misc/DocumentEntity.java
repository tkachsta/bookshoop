package com.example.MyBookShopApp.model.entities.Misc;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "document")
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_document")
    private Long documentId;
    @Column
    private String slug;
    @Column(name = "sort_index")
    private Integer sortIndex;
    @Column(columnDefinition = "TEXT")
    private String text;
    @Column
    private String title;
}
