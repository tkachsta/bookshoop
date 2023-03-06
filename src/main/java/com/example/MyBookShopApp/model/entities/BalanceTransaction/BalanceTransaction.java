package com.example.MyBookShopApp.model.entities.BalanceTransaction;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Users.UserEntity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "balance_transaction")
public class BalanceTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Integer transactionId;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_book", referencedColumnName = "id_book")
    private BookEntity book;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private UserEntity user;
    @Column
    private String description;
    @Column
    private Timestamp timestamp;
    @Column
    private Integer value;

}
