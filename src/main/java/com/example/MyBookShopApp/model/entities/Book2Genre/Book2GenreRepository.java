package com.example.MyBookShopApp.model.entities.Book2Genre;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Book2GenreRepository extends JpaRepository<Book2Genre, Book2GenreKey> {
    @Query("SELECT B2G.book FROM Book2Genre B2G WHERE B2G.genre.slug = :slug")
    Page<BookEntity> findBooksByGenre(@Param("slug") String slug, Pageable page);

}
