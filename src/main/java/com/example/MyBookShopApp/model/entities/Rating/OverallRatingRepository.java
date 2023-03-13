package com.example.MyBookShopApp.model.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverallRatingRepository extends JpaRepository<OverallRatingBook, Integer> {
}
