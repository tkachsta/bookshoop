package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.BookService;
import com.example.MyBookShopApp.model.dtos.RecommendedBooksDto;
import com.example.MyBookShopApp.model.dtos.SearchWordDto;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Book2Author.Book2Author;
import com.example.MyBookShopApp.model.entities.Tag.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class RecentBooksController extends AbstractHeaderController {
    private final BookService bookService;
    @Autowired
    public RecentBooksController(BookService bookService) {
        this.bookService = bookService;
    }



    @ModelAttribute("recentBooksPage")
    public List<BookEntity> getRecentBooksPage() {
        return new ArrayList<>();
    }
    @ModelAttribute("currentTag")
    public TagEntity getCurrentTag() {
        return new TagEntity();
    }



    @GetMapping("/recommended")
    @ResponseBody
    public RecommendedBooksDto getBookPage(@RequestParam("offset") Integer offset,
                                           @RequestParam("limit") Integer limit) {
        List<BookEntity> bookEntities = bookService.getRecommendedBooksPage(offset, limit).getContent();
        return new RecommendedBooksDto(bookEntities.size(), bookEntities);
    }

    @GetMapping("/recent")
    @ResponseBody
    public Object getRecentPageBooks(@RequestParam(value = "offset", required = false) Integer offset,
                                     @RequestParam(value = "limit", required = false) Integer limit,
                                     @RequestParam(value = "from", required = false) String from,
                                     @RequestParam(value = "to", required = false) String to,
                                     Model model) {
        return bookService.getPageOfRecentBooks(from, to, offset, limit, model);
    }

}
