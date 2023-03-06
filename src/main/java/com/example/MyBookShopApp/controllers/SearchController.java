package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.BookService;
import com.example.MyBookShopApp.model.dtos.RecommendedBooksDto;
import com.example.MyBookShopApp.model.dtos.SearchWordDto;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Book2Author.Book2Author;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final BookService bookService;

    public SearchController(BookService bookService) {
        this.bookService = bookService;
    }
    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }
    @ModelAttribute("searchResults")
    public List<BookEntity> searchResults() {
        return new ArrayList<>();
    }
    @ModelAttribute("recommendedBooks")
    public List<BookEntity> recommendedBooks(){
        return bookService.getRecommendedBooksPage(0, 6).getContent();
    }
    @GetMapping(value = {"","/{searchWord}"})
    public String getSearchResults(
            @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto,
            Model model) {
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                bookService.getPageOfRecommendedBooks(
                        searchWordDto.getExample(), 0, 20).getContent());
        return "/search/index";
    }
    @GetMapping("/page/{searchWord}")
    @ResponseBody
    public RecommendedBooksDto getNextSearch(@RequestParam("offset") Integer offset,
                                             @RequestParam("limit") Integer limit,
                                             @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto) {
        List<BookEntity> recommendedBooks = bookService.getPageOfRecommendedBooks(searchWordDto.getExample(), offset, limit).getContent();
        return new RecommendedBooksDto(recommendedBooks().size(), recommendedBooks);
    }
}






