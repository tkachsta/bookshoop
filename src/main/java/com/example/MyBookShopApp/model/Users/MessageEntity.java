package com.example.MyBookShopApp.model.Users;
import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "message")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_message")
    private Long messageId;
    @Column
    private String email;
    @Column
    private String name;
    @Column
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    @Column
    private Timestamp timestamp;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private UserEntity user;


}
