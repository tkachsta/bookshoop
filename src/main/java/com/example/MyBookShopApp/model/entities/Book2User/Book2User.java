package com.example.MyBookShopApp.model.entities.Book2User;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Users.UserEntity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Table
@Entity
public class Book2User {

    @EmbeddedId
    private Book2UserKey book2UserKey;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private UserEntity user;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_book", insertable = false, updatable = false)
    private BookEntity book;
    @Column
    private Timestamp timestamp;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", referencedColumnName = "id_book2user")
    private Book2UserType book2UserType;



}
