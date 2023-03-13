package com.example.MyBookShopApp.model.entities.Rating;

import com.example.MyBookShopApp.model.entities.Users.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "book_rating")
@Entity
@Getter
@Setter
public class BookRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rating")
    private Integer ratingId;
    @Column
    private Integer value;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private UserEntity user;

}
