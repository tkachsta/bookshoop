package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.model.dtos.SearchWordDto;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHeaderController {

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }
    @ModelAttribute("searchResults")
    public List<BookEntity> searchResults(){
        return new ArrayList<>();
    }
    @ModelAttribute("booksNumberPostponed")
    public Integer numberPostponedBooks(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("postponedContents")) {
                    if (cookie.getValue().isEmpty()) {
                        return 0;
                    }
                    return cookie.getValue().split("/").length;
                }
            }
        }
        return 0;
    }
    @ModelAttribute("booksNumberToBuy")
    public Integer numberBookToBuy(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null ) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cartContents")) {
                    if (cookie.getValue().isEmpty()) {
                        return 0;
                    }
                    return cookie.getValue().split("/").length;
                }
            }
        }
        return 0;
    }


}
