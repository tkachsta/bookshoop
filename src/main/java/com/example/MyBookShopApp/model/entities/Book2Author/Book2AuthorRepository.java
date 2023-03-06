package com.example.MyBookShopApp.model.entities.Book2Author;
import com.example.MyBookShopApp.model.entities.Author.AuthorEntity;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface Book2AuthorRepository extends JpaRepository<Book2Author, Integer> {
    List<Book2Author> findAllBy();
    @Query("SELECT DISTINCT(B2A.author) FROM Book2Author B2A")
    List<AuthorEntity> findDistinctAuthors();
    @Query("SELECT DISTINCT(B2A.author) FROM Book2Author B2A WHERE B2A.author.lastName = :lastName")
    List<AuthorEntity> findBooksByAuthorLastName(@Param("lastName") String lastName);
    @Query("SELECT B2A.book FROM Book2Author B2A WHERE B2A.author.slug = :slug")
    Page<BookEntity> findBooksByAuthorSlug(@Param("slug") String slug, Pageable nextPage);

}
