package com.example.MyBookShopApp.model.entities.Users;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_contact")
public class UserContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_message")
    private Long contactId;
    @Column
    private boolean approved;
    @Column
    private String code;
    @Column(name = "code_time")
    private Timestamp codeTime;
    @Column(name = "code_trails")
    private Integer codeTrails;
    @Column
    private String contact;
    @Column
    private Integer type;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private UserEntity user;

}
