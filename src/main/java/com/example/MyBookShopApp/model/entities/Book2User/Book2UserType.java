package com.example.MyBookShopApp.model.entities.Book2User;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "book2user_type")
public class Book2UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book2user")
    private Integer book2userId;
    @Column
    private String code;
    @Column
    private String name;
}
