package com.example.MyBookShopApp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity> findAllBy();

}
