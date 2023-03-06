package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.BookService;
import com.example.MyBookShopApp.model.entities.Author.AuthorEntity;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BooksRestApiController {
    private final BookService bookService;

    @Autowired
    public BooksRestApiController(BookService bookService) {
        this.bookService = bookService;
    }
    @Operation(summary = "Get list of books by author last name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found books by author last name"),
            @ApiResponse(responseCode = "200", description = "Not books found by author")
    })
    @GetMapping("/books/by-author")
    public ResponseEntity<List<AuthorEntity>> booksByAuthor(@RequestParam("author") String author) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(author));
    }
    @Operation(summary = "Get list of books by book title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found books by book title"),
            @ApiResponse(responseCode = "200", description = "Not books found by book title")
    })
    @GetMapping("/books/by-title")
    public ResponseEntity<List<BookEntity>> booksByTitle(@RequestParam("title") String title) {
        return ResponseEntity.ok(bookService.getBooksByTitle(title));
    }
    @Operation(summary = "Get list of books by price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found books by price"),
            @ApiResponse(responseCode = "200", description = "Not books found by price")
    })
    @GetMapping("/books/by-price")
    public ResponseEntity<List<BookEntity>> booksByPrice(@RequestParam("price") Integer price) {
        return ResponseEntity.ok(bookService.getBooksByPrice(price));
    }
    @Operation(summary = "Get list of books between min and max price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found books within defined interval"),
            @ApiResponse(responseCode = "200", description = "Not books found within defined interval")
    })
    @GetMapping("/books/by-price-range")
    public ResponseEntity<List<BookEntity>> booksByPriceBetween(@RequestParam("min") Integer min,
                                                         @RequestParam("max") Integer max) {
        return ResponseEntity.ok(bookService.getBooksByPriceBetween(min, max));
    }
    @Operation(summary = "Get list of bestselling books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found best selling books"),
            @ApiResponse(responseCode = "200", description = "Not bestselling books found")
    })
    @GetMapping("/books/by-bestseller")
    public ResponseEntity<List<BookEntity>> booksByBestseller() {
        return ResponseEntity.ok(bookService.getBestsellersBooks());
    }
    @Operation(summary = "Get list of books with max discount")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found books with max discount"),
            @ApiResponse(responseCode = "200", description = "There are not discount at this time")
    })
    @GetMapping("/books/by-max-discount")
    public ResponseEntity<List<BookEntity>> booksByMaxDiscount() {
        return ResponseEntity.ok(bookService.getBooksByMaxDiscount());
    }






}
