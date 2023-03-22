package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.BookService;
import com.example.MyBookShopApp.data.ReviewService;
import com.example.MyBookShopApp.data.TagService;
import com.example.MyBookShopApp.data.mapper.MapperToBookDto;
import com.example.MyBookShopApp.model.dtos.BookDto;
import com.example.MyBookShopApp.model.dtos.SearchWordDto;
import com.example.MyBookShopApp.model.dtos.tags.TagDto;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.request.RateBookRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
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
    private final MapperToBookDto mapperToBookDto;
    @Autowired
    public MainPageController(BookService bookService,
                              TagService tagService,
                              ReviewService reviewService,
                              MapperToBookDto mapperToBookDto) {
        this.bookService = bookService;
        this.tagService = tagService;
        this.reviewService = reviewService;
        this.mapperToBookDto = mapperToBookDto;
    }



    @ModelAttribute("recommendedBooks")
    public List<BookDto> recommendedBooks(){
        List<BookEntity> bookEntities = bookService.getRecommendedBooksPage(0, 6).getContent();
        return bookEntities.stream().map(mapperToBookDto::convertToDto).toList();
    }
    @ModelAttribute("recentBooks")
    public List<BookDto> recentBooks(){
        List<BookEntity> bookEntities = bookService.getRecentBooksForSlider(0,6).getContent();
        return bookEntities.stream().map(mapperToBookDto::convertToDto).toList();
    }
    @ModelAttribute("popularBooks")
    public List<BookDto> popularBooks(){
        List<BookEntity> bookEntities = bookService.getPopularBooksPage(0,6).getContent();
        return bookEntities.stream().map(mapperToBookDto::convertToDto).toList();
    }
    @ModelAttribute("booksByTags")
    public List<TagDto> getTags() {
        return tagService.getExistingTags();
    }



    @GetMapping()
    public ModelAndView mainPage() {
        return new ModelAndView("index");
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
    @PostMapping(value = "/rateBook")
    public ModelAndView rateBook(@RequestBody RateBookRequest rateBook) {

        bookService.rateBook(rateBook.getBookId(), rateBook.getValue());
        return new ModelAndView("redirect:/books/" + rateBook.getBookId());

    }
    @PostMapping(value = "/rateBookReview")
    public ResponseEntity<?> rateBookReview(@RequestParam (name = "reviewid") Integer reviewId,
                                         @RequestParam (name = "value") Integer value) {

        reviewService.rateReview(reviewId, value);
        return new ResponseEntity<>(HttpStatus.OK);
    }







}
