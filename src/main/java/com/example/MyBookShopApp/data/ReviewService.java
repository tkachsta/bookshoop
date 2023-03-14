package com.example.MyBookShopApp.data;
import com.example.MyBookShopApp.model.entities.Book.*;
import com.example.MyBookShopApp.model.entities.Users.UserEntity;
import com.example.MyBookShopApp.model.entities.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private final BookReviewRepository bookReviewRepository;
    private final BookReviewLikeRepository bookReviewLikeRepository;
    private final UserRepository userRepository;
    @Autowired
    public ReviewService(BookReviewRepository bookReviewRepository,
                         BookReviewLikeRepository bookReviewLikeRepository,
                         UserRepository userRepository) {
        this.bookReviewRepository = bookReviewRepository;
        this.bookReviewLikeRepository = bookReviewLikeRepository;
        this.userRepository = userRepository;
    }

    public List<BookReview> getBookReviews(BookEntity book) {
        List<BookReview> bookReviews = bookReviewRepository.findAllByBook(book);
        bookReviews.sort(Comparator.comparing(BookReview::getTime).reversed());
        return bookReviews;
    }

    public boolean rateReview(Integer reviewId, Integer value) {
        Optional<BookReview> bookReview = bookReviewRepository.findById(reviewId);
        UserEntity user = userRepository.findByUserId(150L);
        if (bookReview.isPresent()) {
            BookReviewLike reviewLike = new BookReviewLike();
            reviewLike.setTime(Timestamp.valueOf(LocalDateTime.now()));
            reviewLike.setBookReview(bookReview.get());
            reviewLike.setUser(user);
            reviewLike.setValue(value);
            bookReviewLikeRepository.saveAndFlush(reviewLike);
            return true;
        }
        return false;
    }

}
