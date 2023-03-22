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

import java.util.List;

@RestController
@RequestMapping("/postponed")
public class BookshopPostponedController extends AbstractHeaderController {

    private final LinkBook2UserService linkBook2UserService;
    private final CartStatus cartStatus;
    private final PostponedStatus postponedStatus;
    public BookshopPostponedController(LinkBook2UserService linkBook2UserService,
                                       CartStatus cartStatus,
                                       PostponedStatus postponedStatus) {
        this.linkBook2UserService = linkBook2UserService;
        this.cartStatus = cartStatus;
        this.postponedStatus = postponedStatus;
    }



    @ModelAttribute("allBooksToBuy")
    public String moveBooksFromPostponedToCart() {
        return String.join(",",
                postponedStatus.getStatusCollection()
                .stream()
                .map(BookEntity::getSlug)
                .toList());
    }



    @GetMapping
    public ModelAndView handlePostponedRequest(Model model) {

        List<BookEntity> bookEntities = numberPostponedBooks();
        if(bookEntities.isEmpty()) {
            model.addAttribute("isPostponedEmpty", true);
        } else {
            model.addAttribute("isPostponedEmpty", false);
        }
        return new ModelAndView("/postponed");

    }
    @PostMapping("/changeBookStatus/{slug}")
    public ResponseEntity<?> handleChangeBooksStatus(@PathVariable("slug") String slug, Model model) {

        if (linkBook2UserService.linkBookWithUser(slug, cartStatus, postponedStatus)) {
            model.addAttribute("isPostponedEmpty", false);
            return new ResponseEntity<>(new TrueResponse(true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new FalseResponse(false, "Не удается обновить статус книги"),
                HttpStatus.NO_CONTENT);

    }
    @PostMapping("/changeBookStatus/postponed/remove/{slug}")
    public ResponseEntity<?> handleRemoveBookFromPostponedRequest(@PathVariable("slug") String slug, Model model) {

        if(linkBook2UserService.unlinkBookWithUser(slug, postponedStatus)) {
            model.addAttribute("isPostponedEmpty", false);
            return new ResponseEntity<>(new TrueResponse(true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new FalseResponse(false, "Не удалить книгу из отложенных"),
                HttpStatus.NO_CONTENT);
    }
    @PostMapping("/buying-all/changeBookStatus/{slug}")
    public ResponseEntity<?> handleBuyingAllBooksStatus (@PathVariable("slug") String slug, Model model) {

        if (linkBook2UserService.bulkBooksTransfer(slug, postponedStatus, cartStatus)) {
            return new ResponseEntity<>(new TrueResponse(true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new FalseResponse(false, "Не удается перенести книги из отложенных в корзину"),
                HttpStatus.NO_CONTENT);
    }








}
