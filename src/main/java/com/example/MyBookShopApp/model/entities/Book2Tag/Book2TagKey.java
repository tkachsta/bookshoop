package com.example.MyBookShopApp.model.entities.Book2Tag;
import lombok.*;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Book2TagKey implements Serializable {

    @Column(name = "id_book")
    private Integer book;
    @Column(name = "id_tag")
    private Integer tag;




}
