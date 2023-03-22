package com.example.MyBookShopApp.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserId(Long userId);
    UserEntity findByEmailIs(String email);
    UserEntity findByName(String username);
    @Query("SELECT MAX(u.userId) FROM UserEntity u")
    int currentMaxId();
}
