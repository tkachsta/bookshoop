package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.*;
import com.example.MyBookShopApp.data.BookService;
import com.example.MyBookShopApp.exceptions.ApiWrongParameterException;
import com.example.MyBookShopApp.model.entities.Author.AuthorEntity;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.response.ApiResponse.BookApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
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
    public ResponseEntity<BookApiResponse<BookEntity>> booksByTitle(@RequestParam("title") String title)
            throws ApiWrongParameterException {
        BookApiResponse<BookEntity> response = new BookApiResponse<>();
        List<BookEntity> bookEntities = bookService.getBooksByTitle(title);
        response.setDebugMessage("Successful request");
        response.setMessage("Dta size: " + bookEntities.size() + " elements");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(bookEntities);
        return ResponseEntity.ok(response);
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


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<BookApiResponse<BookEntity>> handleMissingServletParameterException(Exception exception) {
        return new ResponseEntity<>(
                new BookApiResponse<>(
                        HttpStatus.BAD_REQUEST, "Missing required parameter", exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiWrongParameterException.class)
    public ResponseEntity<BookApiResponse<BookEntity>> handleBookstoreApiWrongParameterException(Exception exception) {
        return new ResponseEntity<>(
                new BookApiResponse<>(
                        HttpStatus.BAD_REQUEST, "Bad parameter value...", exception), HttpStatus.BAD_REQUEST);
    }






}
