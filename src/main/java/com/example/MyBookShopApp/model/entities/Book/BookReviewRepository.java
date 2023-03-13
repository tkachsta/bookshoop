package com.example.MyBookShopApp.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookReviewRepository extends JpaRepository<BookReview, Integer> {
    List<BookReview> findAllByBook(BookEntity book);

}
