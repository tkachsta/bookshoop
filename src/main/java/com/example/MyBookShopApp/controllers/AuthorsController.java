package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.AuthorService;
import com.example.MyBookShopApp.model.components.AuthorBiographyRendering;
import com.example.MyBookShopApp.model.dtos.RecommendedBooksDto;
import com.example.MyBookShopApp.model.dtos.SearchWordDto;
import com.example.MyBookShopApp.model.entities.Author.AuthorEntity;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AuthorsController {
    private final AuthorService authorService;
    @Autowired
    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }
    @ModelAttribute("searchResults")
    public List<BookEntity> searchResults(){
        return new ArrayList<>();
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
            model.addAttribute("authorBooks", authorService.getAuthorBooks(0, 6, slug).getContent());
            model.addAttribute("currentAuthor", author);
            model.addAttribute("shortBio", authorService.getShortDescription(author.getDescription()));
            model.addAttribute("longBio", authorService.getLongDescription());
            return new ModelAndView("/authors/slug");
        }
        List<BookEntity> bookEntities = authorService.getAuthorBooks(offset, limit, slug).getContent();
        return new RecommendedBooksDto(bookEntities.size(), bookEntities);

    }
    @GetMapping(value = "/books/author/{slug}")
    @ResponseBody
    public Object getAuthorBooksPage(@RequestParam(value = "offset", required = false) Integer offset,
                                     @RequestParam(value = "limit", required = false) Integer limit,
                                     @PathVariable String slug, Model model) {
        if (offset == null) {
            AuthorEntity author = authorService.getAuthor(slug);
            model.addAttribute("authorBooks", authorService.getAuthorBooks(0, 20, slug).getContent());
            model.addAttribute("currentAuthor", author);
            return new ModelAndView("/books/author");
        }
        List<BookEntity> bookEntities = authorService.getAuthorBooks(offset, limit, slug).getContent();
        return new RecommendedBooksDto(bookEntities.size(), bookEntities);
    }



}
