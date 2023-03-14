package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.BookService;
import com.example.MyBookShopApp.data.ReviewService;
import com.example.MyBookShopApp.data.TagService;
import com.example.MyBookShopApp.model.dtos.SearchWordDto;
import com.example.MyBookShopApp.model.dtos.tags.TagDto;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController extends AbstractHeaderController {
    private final BookService bookService;
    private final TagService tagService;
    private final ReviewService reviewService;

    @Autowired
    public MainPageController(BookService bookService, TagService tagService, ReviewService reviewService) {
        this.bookService = bookService;
        this.tagService = tagService;
        this.reviewService = reviewService;
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



    @GetMapping()
    public ModelAndView mainPage() {
        return new ModelAndView("index");
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

    @PostMapping(value = "/rateBook")
    public ModelAndView rateBook(@RequestParam (name = "bookId") String bookId,
                                 @RequestParam (name = "value") Integer value) {

        bookService.rateBook(bookId, value);
        return new ModelAndView("redirect:/books/" + bookId);

    }
    @PostMapping(value = "/rateBookReview")
    public ResponseEntity<?> rateBookReview(@RequestParam (name = "reviewid") Integer reviewId,
                                         @RequestParam (name = "value") Integer value) {

        reviewService.rateReview(reviewId, value);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
