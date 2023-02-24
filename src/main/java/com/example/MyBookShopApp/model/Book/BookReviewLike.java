package com.example.MyBookShopApp.model.Book;
import com.example.MyBookShopApp.model.Users.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "book_review_like")
public class BookReviewLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bookreviewlike")
    private Long bookReviewLikeId;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private UserEntity user;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_bookreview", referencedColumnName = "id_bookreview")
    private BookReview bookReview;
    @Column
    private Timestamp time;
    @Column
    private Integer value;
}
