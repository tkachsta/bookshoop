package com.example.MyBookShopApp.model.entities.Book2Tag;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Book2TagRepository extends JpaRepository<Book2Tag, Book2TagKey> {
    @Query("SELECT B2T.book FROM Book2Tag B2T WHERE B2T.tag.tagId = :id")
    Page<BookEntity> findBooksByTag(@Param("id") Integer tagId, Pageable page);


}
