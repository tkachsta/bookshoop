package com.example.MyBookShopApp.model.entities.Book;

import com.example.MyBookShopApp.model.dtos.rating.RatingStar;
import com.example.MyBookShopApp.model.dtos.review.ReviewTextRendering;
import com.example.MyBookShopApp.model.entities.Rating.BookRating;
import com.example.MyBookShopApp.model.entities.Users.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "book_review")
public class BookReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_bookreview")
    private Integer bookreviewId;
    @Column(columnDefinition = "TEXT")
    private String text;
    @Column
    private Timestamp time;
    @OneToMany(mappedBy = "bookReview")
    private Set<BookReviewLike> reviewLikes;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_rating", referencedColumnName = "id_rating")
    private BookRating rating;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private UserEntity user;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_book", referencedColumnName = "id_book")
    private BookEntity book;

    public ReviewTextRendering getTextReviewRendering() {
        String[] text = this.text.split(" ");
        ReviewTextRendering textRendering = new ReviewTextRendering();
        if (text.length < 60) {
            textRendering.setSpoilerVisible(this.text);
            textRendering.setSpoilerHide(null);
            return textRendering;
        }
        String visibleSpoiler = String.join(" ", Arrays.copyOfRange(text, 0, 60));
        String hideSpoiler = String.join(" ", Arrays.copyOfRange(text, 60, text.length - 1));
        textRendering.setSpoilerVisible(visibleSpoiler);
        textRendering.setSpoilerHide(hideSpoiler);
        return textRendering;
    }
    public List<RatingStar> getUserRating() {
        List<RatingStar> ratingStarList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            RatingStar ratingStar = new RatingStar();
            if (i < this.rating.getValue()) {
                ratingStar.setTrue(true);
            }
            ratingStarList.add(ratingStar);
        }
        return ratingStarList;
    }
    public String getFormattedDate() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(this.time);
    }
    public long reviewLikes() {
        return this.reviewLikes.stream().filter(v -> v.getValue() == 1).toList().size();
    }
    public long reviewDislikes() {
        return this.reviewLikes.stream().filter(v -> v.getValue() == -1).toList().size();
    }


}
