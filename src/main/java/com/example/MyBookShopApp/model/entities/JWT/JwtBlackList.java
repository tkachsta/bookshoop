package com.example.MyBookShopApp.model.entities.JWT;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;

@Entity
@Table
@Setter
@Getter
public class JwtBlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String token;


}
