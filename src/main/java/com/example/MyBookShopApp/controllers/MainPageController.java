package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.BookService;
import com.example.MyBookShopApp.data.TagService;
import com.example.MyBookShopApp.model.dtos.SearchWordDto;
import com.example.MyBookShopApp.model.dtos.tags.TagDto;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Book2Author.Book2Author;
import com.example.MyBookShopApp.model.entities.Tag.TagEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {
    private final BookService bookService;
    private final TagService tagService;

    @Autowired
    public MainPageController(BookService bookService, TagService tagService) {
        this.bookService = bookService;
        this.tagService = tagService;
    }
    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }
    @ModelAttribute("searchResults")
    public List<BookEntity> searchResults(){
        return new ArrayList<>();
    }
    @ModelAttribute("recommendedBooks")
    public List<BookEntity> recommendedBooks(){
        return bookService.getRecommendedBooksPage(0, 6).getContent();
    }
    @ModelAttribute("recentBooks")
    public List<BookEntity> recentBooks(){
        return bookService.getRecentBooksForSlider(0,6).getContent();
    }
    @ModelAttribute("popularBooks")
    public List<BookEntity> popularBooks(){
        return bookService.getPopularBooksPage(0,6).getContent();
    }
    @ModelAttribute("booksByTags")
    public List<TagDto> getTags() {
        return tagService.getExistingTags();
    }





    @Operation(summary = "Get main page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found main page")
    })
    @GetMapping()
    public String mainPage() {
        return "index";
    }
    @GetMapping("/postponed")
    public String postponedPage() {
        return "postponed";
    }
    @GetMapping("/cart")
    public String cartPage() {
        return "cart";
    }
    @GetMapping("/signin")
    public String signingPage() {
        return "signin";
    }
    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }
    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }
    @GetMapping("/faq")
    public String faqPage() {
        return "faq";
    }
    @GetMapping("/contacts")
    public String contactsPage() {
        return "contacts";
    }
    @GetMapping("/my")
    public String myPage() {
        return "my";
    }
    @GetMapping("/profile")
    public String myProfile() {
        return "profile";
    }




}
