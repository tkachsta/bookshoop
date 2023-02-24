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
@RequestMapping("/books")
public class PopularBooksController {

    private final BookService bookService;
    @Autowired
    public PopularBooksController(BookService bookService) {
        this.bookService = bookService;
    }
    @ModelAttribute("popularBooks")
    public List<Book2Author> getPopularBooks() {
        List<Book2Author> book2Authors = bookService.getPopularBooks();
        return book2Authors;
    }
    @GetMapping("/popular")
    public String popularBooks() {
        return "books/popular";
    }
}
