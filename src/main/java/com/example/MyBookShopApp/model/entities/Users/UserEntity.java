package com.example.MyBookShopApp.model.entities.Users;

import com.example.MyBookShopApp.model.entities.Book.BookReview;
import com.example.MyBookShopApp.model.entities.Rating.BookRating;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Integer userId;
    @OneToMany(mappedBy = "user")
    private Set<BookReview> bookReview;
    @OneToMany(mappedBy = "user")
    private Set<BookRating> userRatings;
    @Column
    private Integer balance;
    @Column
    private String hash;
    @Column
    private String name;
    @Column(name = "reg_date")
    private Timestamp regDate;


    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String password;

}
