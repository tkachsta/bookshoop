package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.LinkBook2UserService;
import com.example.MyBookShopApp.data.booklifecycle.ArchivedStatus;
import com.example.MyBookShopApp.data.booklifecycle.CartStatus;
import com.example.MyBookShopApp.data.booklifecycle.PostponedStatus;
import com.example.MyBookShopApp.model.response.FalseResponse;
import com.example.MyBookShopApp.model.response.TrueResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/unlink")
public class UnlinkBookController {

    private final PostponedStatus postponedStatus;
    private final CartStatus cartStatus;
    private final LinkBook2UserService linkBook2UserService;

    Logger logger = LoggerFactory.getLogger(UnlinkBookController.class);

    @Autowired
    public UnlinkBookController(PostponedStatus postponedStatus,
                                LinkBook2UserService linkBook2UserService,
                                CartStatus cartStatus) {
        this.postponedStatus = postponedStatus;
        this.linkBook2UserService = linkBook2UserService;
        this.cartStatus = cartStatus;
    }

    @PostMapping("/postponed/changeBookStatus/{slug}")
    public ResponseEntity<?> unlinkBookFromPostponed(@PathVariable("slug") String slug) {

        if (linkBook2UserService.unlinkBookWithUser(slug, postponedStatus)) {
            return new ResponseEntity<>(new TrueResponse(true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new FalseResponse(false, "Не удалось удалить связь с книгой"),
                HttpStatus.OK);
    }
    @PostMapping("/cart/changeBookStatus/{slug}")
    public ResponseEntity<?> unlinkBookFromCart(@PathVariable("slug") String slug) {

        if (linkBook2UserService.unlinkBookWithUser(slug, cartStatus)) {
            return new ResponseEntity<>(new TrueResponse(true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new FalseResponse(false, "Не удалось удалить связь с книгой"),
                HttpStatus.OK);
    }

}
