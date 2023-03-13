package com.example.MyBookShopApp.model.entities.Rating;
import com.example.MyBookShopApp.model.dtos.rating.RatingStar;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rating")
@Getter
@Setter
public class OverallRatingBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rating")
    private Integer ratingId;
    @Column(name = "one_star")
    private Integer oneStarRating;
    @Column(name = "two_star")
    private Integer twoStarRating;
    @Column(name = "three_star")
    private Integer threeStarRating;
    @Column(name = "four_star")
    private Integer fourStarRating;
    @Column(name = "five_star")
    private Integer fiveStarRating;
    @OneToOne(mappedBy = "rating")
    private BookEntity bookEntity;
    public List<RatingStar> getBookRating() {
        Integer ratingSum = this.oneStarRating + this.twoStarRating * 2 + this.threeStarRating * 3 +
                this.fourStarRating * 4 + this.fiveStarRating * 5;
        Integer numberOfVoters = this.oneStarRating + this.twoStarRating + this.threeStarRating +
                this.fourStarRating + this.fiveStarRating;
        Integer bookRating = ratingSum / numberOfVoters;

        List<RatingStar> ratingStarList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            RatingStar ratingStar = new RatingStar();
            if (i < bookRating) {
                ratingStar.setTrue(true);
            }
            ratingStarList.add(ratingStar);
        }
        return ratingStarList;
    }
    public Integer getEstimateNumber() {
        return  this.oneStarRating + this.twoStarRating + this.threeStarRating +
                this.fourStarRating + this.fiveStarRating;
    }

}
