package com.example.MyBookShopApp.model.entities.Tag;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tags")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tag")
    private Integer tagId;
    @Column(name = "tag_name")
    private String tagName;



}
