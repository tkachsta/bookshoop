package com.example.MyBookShopApp.model.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FalseResponse {

    private boolean result;
    private String error;


}
