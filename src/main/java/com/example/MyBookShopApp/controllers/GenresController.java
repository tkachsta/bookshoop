package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.GenreService;
import com.example.MyBookShopApp.model.dtos.RecommendedBooksDto;
import com.example.MyBookShopApp.model.dtos.SearchWordDto;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Genre.GenreEntity;
import com.example.MyBookShopApp.model.entities.Tag.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books/genres")
public class GenresController {
    private final GenreService genreService;
    @Autowired
    public GenresController(GenreService genreService) {
        this.genreService = genreService;
    }
    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }
    @ModelAttribute("searchResults")
    public List<BookEntity> searchResults() {
        return new ArrayList<>();
    }
    @ModelAttribute("currentGenre")
    public String getCurrentTag() {
        return new String();
    }
    @ModelAttribute("genresList")
    public List<GenreEntity> getGenresList() {
        return genreService.getParentGenres();
    }
    @ModelAttribute("booksByGenre")
    public List<BookEntity> getBooksList() {
        return new ArrayList<>();
    }

    @GetMapping()
    @ResponseBody
    public ModelAndView genresPage() {
        return new ModelAndView("genres/index");
    }

    @GetMapping(value = "/{genre}")
    @ResponseBody
    public Object getBooksByGenre (@RequestParam(value = "offset", required = false) Integer offset,
                                   @RequestParam(value = "limit", required = false) Integer limit,
                                   @PathVariable String genre, Model model) {
        if (offset == null) {
            model.addAttribute("booksByGenre",
                    genreService.getBooksByGenre(0, 20, genre).getContent());
            model.addAttribute("currentGenre", genre);
            return new ModelAndView("/genres/slug");
        }
        List<BookEntity> bookEntities =
                genreService.getBooksByGenre(offset, limit, genre).getContent();
        return new RecommendedBooksDto(bookEntities.size(), bookEntities);
    }
}
