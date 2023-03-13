package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BookService;
import com.example.MyBookShopApp.model.dtos.SearchWordDto;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

@RestController
@RequestMapping("/cart")
public class BookshopCartController {

    private final BookService bookService;

    public BookshopCartController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }
    @ModelAttribute("searchResults")
    public List<BookEntity> searchResults(){
        return new ArrayList<>();
    }
    @ModelAttribute(name = "bookCart")
    public List<BookEntity> bookEntities() {
        return new ArrayList<>();
    }
    @ModelAttribute(name = "cartPrice")
    public Integer getCartPrice() {return 0;}
    @ModelAttribute(name = "cartOldPrice")
    public Integer getCartOldPrice() {return 0;}

    @PostMapping("/changeBookStatus/{slug}")
    public ModelAndView handleChangeBookStatus(@PathVariable("slug") String slug,
                                               @CookieValue(name = "cartContents", required = false) String cartContents,
                                               HttpServletResponse response, Model model) {

        if (cartContents == null || cartContents.equals("")) {
            Cookie cookie = new Cookie("cartContents", slug.replace(",", "/"));
            cookie.setPath("/cart");
            response.addCookie(cookie);
            model.addAttribute("isCartEmpty", false);
        } else if (!cartContents.contains(slug)) {
            StringJoiner stringJoiner = new StringJoiner("/");
            stringJoiner.add(cartContents).add(slug);
            Cookie cookie = new Cookie("cartContents", stringJoiner.toString());
            cookie.setPath("/cart");
            response.addCookie(cookie);
            model.addAttribute("isCartEmpty", false);
        }

        return new ModelAndView("redirect:/books/" + slug);
    }
    @GetMapping
    public ModelAndView handleCartRequest(@CookieValue(value = "cartContents", required = false) String cartContents,
                                          Model model) {

        if (cartContents == null || cartContents.equals("")) {
            model.addAttribute("isCartEmpty", true);
        } else {
            model.addAttribute("isCartEmpty", false);
            cartContents = cartContents.startsWith("/") ? cartContents.substring(1) : cartContents;
            cartContents = cartContents.endsWith("/") ? cartContents.substring(0, cartContents.length() - 1) : cartContents;
            String[] cookieSlugs = cartContents.split("/");
            List<BookEntity> bookEntities = bookService.getBooksBySlugList(Arrays.asList(cookieSlugs));
            model.addAttribute("bookCart", bookEntities);
            model.addAttribute("cartPrice", bookEntities.stream().mapToInt(BookEntity::getPrice).sum());
            model.addAttribute("cartOldPrice", bookEntities.stream().mapToInt(BookEntity::getPriceOld).sum());
        }
        return new ModelAndView("/cart");

    }

    @PostMapping("/changeBookStatus/cart/remove/{slug}")
    public ModelAndView handleRemoveBookFromCartRequest(@PathVariable("slug") String slug,
                                                        @CookieValue(name = "cartContents", required = false) String cartContents,
                                                        HttpServletResponse response, Model model) {

        if (cartContents != null && !cartContents.equals("")) {
            ArrayList<String> cookieBooks = new ArrayList<>(Arrays.asList(cartContents.split("/")));
            cookieBooks.remove(slug);
            Cookie cookie = new Cookie("cartContents", String.join("/", cookieBooks));
            cookie.setPath("/cart");
            response.addCookie(cookie);
            model.addAttribute("isCartEmpty", false);
        } else {
            model.addAttribute("isCartEmpty", true);
        }

        return new ModelAndView("redirect:/books/" + slug);
    }





}
