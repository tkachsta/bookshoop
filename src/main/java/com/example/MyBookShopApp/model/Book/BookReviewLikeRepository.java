package com.example.MyBookShopApp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewLikeRepository extends JpaRepository<BookReviewLike, Long> {
}
