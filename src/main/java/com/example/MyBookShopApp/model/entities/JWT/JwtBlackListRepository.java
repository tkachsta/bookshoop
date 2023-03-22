package com.example.MyBookShopApp.model.entities.JWT;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtBlackListRepository extends JpaRepository<JwtBlackList, Integer> {
    Optional<JwtBlackList> findByToken(String token);

}
