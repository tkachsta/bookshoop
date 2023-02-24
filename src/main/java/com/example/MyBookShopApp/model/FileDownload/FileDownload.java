package com.example.MyBookShopApp.model.FileDownload;

import com.example.MyBookShopApp.model.Book.BookEntity;
import com.example.MyBookShopApp.model.Book2User.Book2UserKey;
import com.example.MyBookShopApp.model.Book2User.Book2UserType;
import com.example.MyBookShopApp.model.Users.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Table(name = "file_download")
@Entity
public class FileDownload {

    @EmbeddedId
    private FileDownloadKey fileDownloadKey;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private UserEntity user;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_book", insertable = false, updatable = false)
    private BookEntity book;
    @Column
    private Integer count;

}
