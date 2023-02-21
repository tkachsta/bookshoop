package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/books")
public class PopularBooksController {

    private final BookService bookService;
    @Autowired
    public PopularBooksController(BookService bookService) {
        this.bookService = bookService;
    }
    @ModelAttribute("popularBooks")
    public List<Book> getPopularBooks() {
        return bookService.getBooksData();
    }
    @GetMapping("/popular")
    public String popularBooks() {
        return "books/popular";
    }
}
