package com.example.MyBookShopApp.model.Book2User;
import com.example.MyBookShopApp.model.Book.BookEntity;
import com.example.MyBookShopApp.model.Genre.GenreEntity;
import com.example.MyBookShopApp.model.Users.UserEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Book2UserKey implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private UserEntity user;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_book", insertable = false, updatable = false)
    private BookEntity book;




}
