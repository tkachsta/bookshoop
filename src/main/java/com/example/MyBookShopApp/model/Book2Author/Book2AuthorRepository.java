package com.example.MyBookShopApp.model.Book2Author;
import com.example.MyBookShopApp.model.Author.AuthorEntity;
import com.example.MyBookShopApp.model.Book.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface Book2AuthorRepository extends JpaRepository<Book2Author, Integer> {
    List<Book2Author> findAllBy();
    @Query("SELECT DISTINCT(B2A.author) FROM Book2Author B2A")
    List<AuthorEntity> findDistinctAuthors();
    @Query("SELECT DISTINCT(B2A.book) FROM Book2Author B2A")
    List<BookEntity> findDistinctBooks();

}
