package com.example.MyBookShopApp.model.entities.Bookfile2Type;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class Bookfile2TypeKey implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bookfile", insertable = false, updatable = false)
    private BookFileEntity fileEntity;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_book_file_type", insertable = false, updatable = false)
    private BookFileType fileType;


}
