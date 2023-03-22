package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.LinkBook2UserService;
import com.example.MyBookShopApp.data.booklifecycle.CartStatus;
import com.example.MyBookShopApp.data.booklifecycle.PostponedStatus;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.response.FalseResponse;
import com.example.MyBookShopApp.model.response.TrueResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class BookshopCartController extends AbstractHeaderController {
    private final LinkBook2UserService linkBook2UserService;
    private final CartStatus cartStatus;
    private final PostponedStatus postponedStatus;
    public BookshopCartController(LinkBook2UserService linkBook2UserService,
                                  CartStatus cartStatus,
                                  PostponedStatus postponedStatus) {
        this.linkBook2UserService = linkBook2UserService;
        this.cartStatus = cartStatus;
        this.postponedStatus = postponedStatus;
    }


    @ModelAttribute(name = "bookCart")
    public List<BookEntity> bookEntities() {
        return new ArrayList<>();
    }
    @ModelAttribute(name = "cartPrice")
    public Integer getCartPrice() {return 0;}
    @ModelAttribute(name = "cartOldPrice")
    public Integer getCartOldPrice() {return 0;}


    @GetMapping
    public ModelAndView handleCartRequest(Model model) {

        List<BookEntity> bookEntities = cartStatus.getStatusCollection();
        if (bookEntities.isEmpty()) {
            model.addAttribute("isCartEmpty", true);
        } else {
            model.addAttribute("isCartEmpty", false);
            model.addAttribute("cartPrice", bookEntities.stream().mapToInt(BookEntity::getPrice).sum());
            model.addAttribute("cartOldPrice", bookEntities.stream().mapToInt(BookEntity::getPriceOld).sum());
        }
        return new ModelAndView("/cart");

    }
    @PostMapping("/changeBookStatus/{slug}")
    public ResponseEntity<Object> handleChangeBookStatus(@PathVariable("slug") String slug, Model model) {

        if (linkBook2UserService.linkBookWithUser(slug, postponedStatus, cartStatus)) {
            model.addAttribute("isCartEmpty", false);
            return new ResponseEntity<>(new TrueResponse(true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new FalseResponse(false, "Не удается обновить статус книги"),
                HttpStatus.NO_CONTENT);

    }
    @PostMapping("/changeBookStatus/cart/remove/{slug}")
    public ResponseEntity<Object> handleRemoveBookFromCartRequest(@PathVariable("slug") String slug, Model model) {

        if(linkBook2UserService.unlinkBookWithUser(slug, cartStatus)) {
            model.addAttribute("isCartEmpty", false);
            return new ResponseEntity<>(new TrueResponse(true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new FalseResponse(false, "Не удается удалить книгу из корзины"),
                HttpStatus.NO_CONTENT);

    }


}
