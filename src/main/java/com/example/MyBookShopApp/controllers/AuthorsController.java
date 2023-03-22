package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.AuthorService;
import com.example.MyBookShopApp.data.mapper.MapperToBookDto;
import com.example.MyBookShopApp.model.components.AuthorBiographyRendering;
import com.example.MyBookShopApp.model.dtos.BookDto;
import com.example.MyBookShopApp.model.dtos.BooksListDto;
import com.example.MyBookShopApp.model.dtos.RecommendedBooksDto;
import com.example.MyBookShopApp.model.dtos.SearchWordDto;
import com.example.MyBookShopApp.model.entities.Author.AuthorEntity;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AuthorsController extends AbstractHeaderController {
    private final AuthorService authorService;
    private final MapperToBookDto mapperToBookDto;
    @Autowired
    public AuthorsController(AuthorService authorService, MapperToBookDto mapperToBookDto) {
        this.authorService = authorService;
        this.mapperToBookDto = mapperToBookDto;
    }



    @ModelAttribute("authorsMap")
    public Map<String, List<AuthorEntity>> authorsMap() {
        return authorService.getAuthorsMap();
    }
    @ModelAttribute("authorBooks")
    public List<BookEntity> getAuthorBooks() {
        return new ArrayList<>();
    }
    @ModelAttribute("currentAuthor")
    public AuthorEntity getCurrentAuthor() {
        return new AuthorEntity();
    }
    @ModelAttribute("shortBio")
    public List<String> getShortBio() {
        return new ArrayList<>();
    }
    @ModelAttribute("longBio")
    public List<String> getLongBio() {
        return new ArrayList<>();
    }



    @GetMapping("/authors")
    public ModelAndView authorsPage() {
        return new ModelAndView("authors/index");
    }
    @GetMapping(value = "/authors/{slug}")
    @ResponseBody
    public Object getAuthorPage(@RequestParam(value = "offset", required = false) Integer offset,
                                @RequestParam(value = "limit", required = false) Integer limit,
                                @PathVariable String slug, Model model) {

        if (offset == null) {
            AuthorEntity author = authorService.getAuthor(slug);
            List<BookEntity> bookEntities = authorService.getAuthorBooks(0, 6, slug).getContent();
            model.addAttribute("currentAuthor", author);
            model.addAttribute("authorBooks", bookEntities.stream().map(mapperToBookDto::convertToDto).toList());
            model.addAttribute("shortBio", authorService.getShortDescription(author.getDescription()));
            model.addAttribute("longBio", authorService.getLongDescription());
            return new ModelAndView("/authors/slug");
        }
        List<BookEntity> bookEntities = authorService.getAuthorBooks(offset, limit, slug).getContent();
        List<BookDto> bookDtoList = bookEntities.stream().map(mapperToBookDto::convertToDto).toList();
        return new ResponseEntity<>(new BooksListDto(bookDtoList.size(), bookDtoList), HttpStatus.OK);

    }
    @GetMapping(value = "/books/author/{slug}")
    @ResponseBody
    public Object getAuthorBooksPage(@RequestParam(value = "offset", required = false) Integer offset,
                                     @RequestParam(value = "limit", required = false) Integer limit,
                                     @PathVariable String slug, Model model) {
        if (offset == null) {
            AuthorEntity author = authorService.getAuthor(slug);
            List<BookEntity> bookEntities = authorService.getAuthorBooks(0, 20, slug).getContent();
            model.addAttribute("authorBooks", bookEntities.stream().map(mapperToBookDto::convertToDto).toList());
            model.addAttribute("currentAuthor", author);
            return new ModelAndView("/books/author");
        }
        List<BookEntity> bookEntities = authorService.getAuthorBooks(offset, limit, slug).getContent();
        List<BookDto> bookDtoList = bookEntities.stream().map(mapperToBookDto::convertToDto).toList();
        return new ResponseEntity<>(new BooksListDto(bookDtoList.size(), bookDtoList), HttpStatus.OK);
    }



}
