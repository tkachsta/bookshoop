package com.example.MyBookShopApp.model.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    List<GenreEntity> findAllByParentIdIsNull();

}
