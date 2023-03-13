package com.example.MyBookShopApp.model.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity> findAllBy();
    List<BookEntity> findBookEntitiesByTitleContaining(String title);
    List<BookEntity> findBookEntitiesByPriceOldBetween(Integer min, Integer max);
    List<BookEntity> findBookEntitiesByPriceOldIs(Integer price);
    List<BookEntity> findBookEntitiesByIsBestsellerTrue();
    Optional<BookEntity> findBySlugIs(String slug);
    @Query(value = "SELECT * FROM books WHERE discount = (SELECT MAX(discount) FROM books)",
            nativeQuery = true)
    List<BookEntity> getBookEntitiesByMaxDiscount();
    Page<BookEntity> findBookEntitiesByTitleContaining(String bookTitle, Pageable nextPage);
    List<BookEntity> findAllBySlugIsIn(List<String> slugs);
    @Query("SELECT b FROM BookEntity b WHERE b.pub_date > :param ORDER BY b.pub_date DESC")
    Page<BookEntity> findRecentBooks(@Param("param") LocalDateTime dateTime, Pageable pageable);
    @Query("SELECT b FROM BookEntity b WHERE b.pub_date BETWEEN :from AND :to ORDER BY b.pub_date DESC")
    Page<BookEntity> findRecentBooks(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to, Pageable pageable);
}
