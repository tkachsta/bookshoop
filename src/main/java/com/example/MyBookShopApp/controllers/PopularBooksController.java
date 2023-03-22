package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.BookService;
import com.example.MyBookShopApp.data.mapper.MapperToBookDto;
import com.example.MyBookShopApp.model.dtos.BookDto;
import com.example.MyBookShopApp.model.dtos.BooksListDto;
import com.example.MyBookShopApp.model.dtos.RecommendedBooksDto;
import com.example.MyBookShopApp.model.dtos.SearchWordDto;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Book2Author.Book2Author;
import com.example.MyBookShopApp.model.entities.Tag.TagEntity;
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
@RequestMapping("/books")
public class PopularBooksController extends AbstractHeaderController {
    private final BookService bookService;
    private final MapperToBookDto mapperToBookDto;
    @Autowired
    public PopularBooksController(BookService bookService,
                                  MapperToBookDto mapperToBookDto) {
        this.bookService = bookService;
        this.mapperToBookDto = mapperToBookDto;
    }



    @ModelAttribute("recentBooksPage")
    public List<BookEntity> getRecentBooksPage() {
        return new ArrayList<>();
    }
    @ModelAttribute("popularBooksPage")
    public List<BookDto> getPopularBooksPage() {
        return new ArrayList<>();
    }
    @ModelAttribute("currentTag")
    public TagEntity getCurrentTag() {
        return new TagEntity();
    }




    @GetMapping(value = "/popular")
    @ResponseBody
    public Object getPopularBooksResult (@RequestParam(value = "offset", required = false) Integer offset,
                                         @RequestParam(value = "limit", required = false) Integer limit,
                                         Model model) {
        if (offset == null) {
            List<BookEntity> bookEntities = bookService.getPopularBooksPage(0, 20).getContent();
            model.addAttribute("popularBooksPage", bookEntities.stream().map(mapperToBookDto::convertToDto).toList());
            return new ModelAndView("/books/popular");
        }
        List<BookEntity> bookEntities = bookService.getPopularBooksPage(offset, limit).getContent();
        List<BookDto> bookDtoList = bookEntities.stream().map(mapperToBookDto::convertToDto).toList();
        return new ResponseEntity<>(new BooksListDto(bookDtoList.size(), bookDtoList), HttpStatus.OK);

    }
    @GetMapping("/recommended")
    @ResponseBody
    public ResponseEntity<BooksListDto> getBookPage(@RequestParam("offset") Integer offset,
                                                    @RequestParam("limit") Integer limit) {

        List<BookEntity> bookEntities = bookService.getRecommendedBooksPage(offset, limit).getContent();
        List<BookDto> bookDtoList = bookEntities.stream().map(mapperToBookDto::convertToDto).toList();
        return new ResponseEntity<>(new BooksListDto(bookDtoList.size(), bookDtoList), HttpStatus.OK);

    }

    @GetMapping("/recent")
    @ResponseBody
    public Object getRecentPageBooks(@RequestParam(value = "offset", required = false) Integer offset,
                                     @RequestParam(value = "limit", required = false) Integer limit,
                                     @RequestParam(value = "from", required = false) String from,
                                     @RequestParam(value = "to", required = false) String to,
                                     Model model) {

        List<BookEntity> bookEntities = bookService.getPageOfRecentBooks(from, to, offset, limit);
        if (offset == null) {
            List<BookDto> books = bookEntities.stream().map(mapperToBookDto::convertToDto).toList();
            model.addAttribute("recentBooksPage", books);
            return new ModelAndView("/books/recent");
        }
        List<BookDto> bookDtoList = bookEntities.stream().map(mapperToBookDto::convertToDto).toList();
        return new ResponseEntity<>(new BooksListDto(bookDtoList.size(), bookDtoList), HttpStatus.OK);

    }
}
