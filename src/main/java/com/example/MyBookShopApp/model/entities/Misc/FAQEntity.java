package com.example.MyBookShopApp.model.entities.Misc;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "faq")
public class FAQEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_faq")
    private Long faqId;
    @Column(name = "sort_index")
    private Integer sortIndex;
    @Column(columnDefinition = "TEXT")
    private String answer;
    @Column
    private String question;

}
