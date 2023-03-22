package com.example.MyBookShopApp.model.entities.Book;
import com.example.MyBookShopApp.model.entities.Book2Author.Book2Author;
import com.example.MyBookShopApp.model.entities.Bookfile2Type.Bookfile2Type;
import com.example.MyBookShopApp.model.entities.Rating.OverallRatingBook;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "books")
@EqualsAndHashCode
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private Integer bookId;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer discount;
    @Column
    private String image;
    @Column(name = "is_bestseller", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean bestseller;
    @Column
    private Integer price;
    @Column
    private LocalDateTime pub_date;
    @Column
    private String slug;
    @Column
    private String title;
    @Column(name = "price_old")
    private Integer priceOld;
    @OneToMany(mappedBy = "bookEntity")
    @JsonIgnore
    List<Bookfile2Type> bookFilesList;
    @JsonSerialize(using = AuthorsStringSerializer.class)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Book2Author> authors;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rating", referencedColumnName = "id_rating")
    @JsonIgnore
    private OverallRatingBook rating;


}
