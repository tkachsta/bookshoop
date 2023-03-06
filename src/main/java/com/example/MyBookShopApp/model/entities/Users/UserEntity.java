package com.example.MyBookShopApp.model.entities.Users;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long userId;
    @Column
    private Integer balance;
    @Column
    private String hash;
    @Column
    private String name;
    @Column(name = "reg_date")
    private Timestamp regDate;
}
