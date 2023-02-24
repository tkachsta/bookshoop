package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.BookService;
import com.example.MyBookShopApp.model.Book.BookEntity;
import com.example.MyBookShopApp.model.Book2Author.Book2Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainPageController {
    private final BookService bookService;
    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }
    @ModelAttribute("recommendedBooks")
    public List<Book2Author> recommendedBooks(){
        return bookService.getBooksData();
    }
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
