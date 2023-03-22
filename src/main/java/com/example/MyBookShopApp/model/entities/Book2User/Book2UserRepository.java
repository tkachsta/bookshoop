package com.example.MyBookShopApp.model.entities.Book2User;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public interface Book2UserRepository extends JpaRepository<Book2User, Integer> {

    @Query("SELECT B2U FROM Book2User B2U WHERE B2U.book2UserType.code IN ('KEPT', 'CART', 'PAID')")
    List<Book2User> getBook2UserByType();
    Optional<Book2User> findByBook2UserKey(Book2UserKey book2UserKey);
    @Query("SELECT B2U.book FROM Book2User B2U WHERE B2U.user = :user AND B2U.book2UserType.code = :code")
    List<BookEntity> findBooksByUserAndStatus(@Param("user") UserEntity user, @Param("code") String code);
    @Modifying
    @Query("DELETE FROM Book2User B2U WHERE B2U.book2UserKey = :key")
    void deleteByKey(@Param("key") Book2UserKey book2UserKey);

}
