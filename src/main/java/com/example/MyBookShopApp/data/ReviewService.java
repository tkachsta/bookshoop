package com.example.MyBookShopApp.data;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Book.BookReview;
import com.example.MyBookShopApp.model.entities.Book.BookReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ReviewService {
    private final BookReviewRepository bookReviewRepository;
    @Autowired
    public ReviewService(BookReviewRepository bookReviewRepository) {
        this.bookReviewRepository = bookReviewRepository;
    }

    public List<BookReview> getBookReviews(BookEntity book) {
        List<BookReview> bookReviews = bookReviewRepository.findAllByBook(book);
        bookReviews.sort(Comparator.comparing(BookReview::getTime).reversed());
        return bookReviews;

    }

}
