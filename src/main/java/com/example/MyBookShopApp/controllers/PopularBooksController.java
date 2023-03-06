package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.BookService;
import com.example.MyBookShopApp.model.dtos.RecommendedBooksDto;
import com.example.MyBookShopApp.model.dtos.SearchWordDto;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Book2Author.Book2Author;
import com.example.MyBookShopApp.model.entities.Tag.TagEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class PopularBooksController {
    private final BookService bookService;
    @Autowired
    public PopularBooksController(BookService bookService) {
        this.bookService = bookService;
    }
    @ModelAttribute("popularBooksPage")
    public List<BookEntity> getPopularBooksPage() {
        return new ArrayList<>();
    }
    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }
    @ModelAttribute("searchResults")
    public List<BookEntity> searchResults() {
        return new ArrayList<>();
    }
    @ModelAttribute("currentTag")
    public TagEntity getCurrentTag() {
        return new TagEntity();
    }
    @GetMapping(value = {"/popular"})
    @ResponseBody
    public Object getPopularBooksResult (@RequestParam(value = "offset", required = false) Integer offset,
                                         @RequestParam(value = "limit", required = false) Integer limit, Model model) {
        if (offset == null) {
            model.addAttribute("popularBooksPage", bookService.getPopularBooksPage(0, 20).getContent());
            return new ModelAndView("/books/popular");
        }
        List<BookEntity> bookEntities = bookService.getPopularBooksPage(offset, limit).getContent();
        return new RecommendedBooksDto(bookEntities.size(), bookEntities);
    }
}
