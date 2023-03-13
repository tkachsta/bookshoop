package com.example.MyBookShopApp.model.entities.Bookfile2Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Bookfile2TypeRepository extends JpaRepository<Bookfile2Type, Bookfile2TypeKey> {
    Bookfile2Type findByHashIs(String hash);

}
