package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.TagService;
import com.example.MyBookShopApp.model.dtos.RecommendedBooksDto;
import com.example.MyBookShopApp.model.dtos.SearchWordDto;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Tag.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books/tag")
public class TagsController {
    private final TagService tagService;
    @Autowired
    public TagsController(TagService tagService) {
        this.tagService = tagService;
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
    @ModelAttribute("booksByTags")
    public List<BookEntity> getBooksByTag() {
        return new ArrayList<>();
    }
    @GetMapping(value = "/{id}")
    @ResponseBody
    public Object getBooksByTag (@RequestParam(value = "offset", required = false) Integer offset,
                                 @RequestParam(value = "limit", required = false) Integer limit,
                                 @PathVariable String id, Model model) {
        if (offset == null) {
            model.addAttribute("booksByTags",
                    tagService.getBooksByTags(0, 20, Integer.valueOf(id)).getContent());
            model.addAttribute("currentTag", tagService.getTagName(Integer.valueOf(id)));
            return new ModelAndView("/tag/index");
        }
        List<BookEntity> bookEntities =
                tagService.getBooksByTags(offset, limit, Integer.valueOf(id)).getContent();
        return new RecommendedBooksDto(bookEntities.size(), bookEntities);
    }
}
