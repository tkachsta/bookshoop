package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.AbstractAuthorityService;
import com.example.MyBookShopApp.data.UserService;
import com.example.MyBookShopApp.model.components.collectioninit.*;
import com.example.MyBookShopApp.model.dtos.SearchWordDto;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractHeaderController extends AbstractAuthorityService {
    @Autowired
    private UserService userService;
    @Autowired
    private BooksCollectionHub booksCollectionHub;


    @Value("${cart-model.statuses.cart}")
    private String cartStatus;
    @Value("${cart-model.statuses.kept}")
    private String keptStatus;
    @Value("${cart-model.statuses.paid}")
    private String paidStatus;
    @Value("${cart-model.statuses.archived}")
    private String archivedStatus;



    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }
    @ModelAttribute("searchResults")
    public List<BookEntity> searchResults(){
        return new ArrayList<>();
    }



    @ModelAttribute("booksNumberPostponed")
    public List<BookEntity> numberPostponedBooks() {
        if (isAuthenticated()) {
            booksCollectionHub.getBooksPostponed().clear();
            booksCollectionHub.getBooksPostponed().addAll(userService.getBooksByUserAndStatus(keptStatus));
        }
        return booksCollectionHub.getBooksPostponed();
    }
    @ModelAttribute("booksNumberToBuy")
    public List<BookEntity> numberBooksToBuy() {
        if (isAuthenticated()) {
            booksCollectionHub.getBooksInCart().clear();
            booksCollectionHub.getBooksInCart().addAll(userService.getBooksByUserAndStatus(cartStatus));
        }
        return booksCollectionHub.getBooksInCart();
    }
    @ModelAttribute("booksNumberBought")
    public List<BookEntity> amountBoughtBooks() {
        booksCollectionHub.getBooksBought().clear();
        booksCollectionHub.getBooksBought().addAll(userService.getBooksByUserAndStatus(paidStatus));
        return booksCollectionHub.getBooksBought();
    }
    @ModelAttribute("booksNumberArchived")
    public List<BookEntity> amountArchivedBooks() {
        booksCollectionHub.getBooksArchived().clear();
        booksCollectionHub.getBooksArchived().addAll(userService.getBooksByUserAndStatus(archivedStatus));
        return booksCollectionHub.getBooksArchived();
    }



}
