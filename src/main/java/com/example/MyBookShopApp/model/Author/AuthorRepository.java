package com.example.MyBookShopApp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {
}
