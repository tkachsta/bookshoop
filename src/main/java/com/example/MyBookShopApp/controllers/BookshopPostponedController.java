package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.BookService;
import com.example.MyBookShopApp.model.dtos.SearchWordDto;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import org.springframework.boot.Banner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/postponed")
public class BookshopPostponedController extends AbstractHeaderController {
    private final BookService bookService;
    public BookshopPostponedController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute(name = "bookPostponed")
    public List<BookEntity> bookEntities() {
        return new ArrayList<>();
    }
    @ModelAttribute(name = "booksPostponed")
    public List<BookEntity> postponedBooks() {return new ArrayList<>();}

    @PostMapping("/changeBookStatus/{slug}")
    public ModelAndView handleChangeBooksStatus(@PathVariable("slug") String slug,
                                                @CookieValue(name = "postponedContents", required = false) String postponedContents,
                                                HttpServletResponse response, Model model) {

        if (postponedContents == null || postponedContents.equals("")) {
            Cookie cookie = new Cookie("postponedContents", slug);
            cookie.setPath("/");
            response.addCookie(cookie);
            model.addAttribute("isPostponedEmpty", false);
        } else if (!postponedContents.contains(slug)) {
            StringJoiner stringJoiner = new StringJoiner("/");
            stringJoiner.add(postponedContents).add(slug);
            Cookie cookie = new Cookie("postponedContents", stringJoiner.toString());
            cookie.setPath("/");
            response.addCookie(cookie);
            model.addAttribute("isPostponedEmpty", false);
        }

        return new ModelAndView("redirect:/books/" + slug);

    }
    @PostMapping("/buying-all/changeBookStatus/{slug}")
    public ModelAndView handleBuyingAllBooksStatus (@PathVariable("slug") String slug,
                                                    @CookieValue(name = "cartContents", required = false) String cartContents,
                                                    HttpServletResponse response, Model model) {

        List<String> booksSlug = List.of(slug.split(","));
        booksSlug.forEach(x -> {
            if (cartContents == null || cartContents.equals("")) {
                Cookie cookie = new Cookie("cartContents", slug.replace(",", "/"));
                cookie.setPath("/");
                response.addCookie(cookie);
                model.addAttribute("isCartEmpty", false);
            } else if (!cartContents.contains(slug)) {
                StringJoiner stringJoiner = new StringJoiner("/");
                stringJoiner.add(cartContents).add(slug);
                Cookie cookie = new Cookie("cartContents", stringJoiner.toString());
                cookie.setPath("/");
                response.addCookie(cookie);
                model.addAttribute("isCartEmpty", false);
            }
        });

        return new ModelAndView("/cart");
    }
    @GetMapping
    public ModelAndView handlePostponedRequest(@CookieValue(value = "postponedContents", required = false) String postponedContents,
                                               Model model) {

        if (postponedContents == null || postponedContents.equals("")) {
            model.addAttribute("isPostponedEmpty", true);
        } else {
            model.addAttribute("isPostponedEmpty", false);
            postponedContents = postponedContents.startsWith("/") ?
                    postponedContents.substring(1) : postponedContents;
            postponedContents = postponedContents.endsWith("/") ?
                    postponedContents.substring(0, postponedContents.length() - 1) : postponedContents;
            String[] cookieSlugs = postponedContents.split("/");
            List<BookEntity> bookEntities = bookService.getBooksBySlugList(Arrays.asList(cookieSlugs));
            model.addAttribute("booksPostponed", bookEntities);
            model.addAttribute("allBooksBuy", bookEntities
                    .stream().map(BookEntity::getSlug).collect(Collectors.joining(",")));
        }

        return new ModelAndView("/postponed");
    }

    @PostMapping("/changeBookStatus/postponed/remove/{slug}")
    public ModelAndView handleRemoveBookFromPostponedRequest(@PathVariable("slug") String slug,
                                                             @CookieValue(name = "postponedContents", required = false) String postponedContents,
                                                             HttpServletResponse response, Model model) {

        if (postponedContents != null && !postponedContents.equals("")) {
            ArrayList<String> cookieBooks = new ArrayList<>(Arrays.asList(postponedContents.split("/")));
            cookieBooks.remove(slug);
            Cookie cookie = new Cookie("postponedContents", String.join("/", cookieBooks));
            cookie.setPath("/");
            response.addCookie(cookie);
            model.addAttribute("isPostponedEmpty", false);
        } else {
            model.addAttribute("isPostponedEmpty", true);
        }

        return new ModelAndView("redirect:/books/" + slug);
    }








}
