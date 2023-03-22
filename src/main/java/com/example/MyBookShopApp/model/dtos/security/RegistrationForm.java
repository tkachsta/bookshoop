package com.example.MyBookShopApp.model.dtos.security;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationForm {
    private String name;
    private String email;
    private String phone;
    private String pass;

}
