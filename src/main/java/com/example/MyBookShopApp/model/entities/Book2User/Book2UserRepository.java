package com.example.MyBookShopApp.model.entities.Book2User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Book2UserRepository extends JpaRepository<Book2User, Book2UserKey> {

    @Query("SELECT B2U FROM Book2User B2U WHERE B2U.book2UserType.code IN ('KEPT', 'CART', 'PAID')")
    List<Book2User> getBook2UserByType();

}
