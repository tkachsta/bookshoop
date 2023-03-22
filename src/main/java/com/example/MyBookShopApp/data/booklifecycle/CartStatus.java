package com.example.MyBookShopApp.data.booklifecycle;
import com.example.MyBookShopApp.model.components.collectioninit.BooksInCart;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartStatus implements BookStatus {
    @Value("${cart-model.statuses.cart}")
    private String cartStatus;
    private final BooksInCart booksInCart;
    @Autowired
    public CartStatus(BooksInCart booksInCart) {
        this.booksInCart = booksInCart;
    }
    public List<BookEntity> getStatusCollection() {
        return booksInCart;
    }
    public String getCurrentStatus() {
        return this.cartStatus;
    }

}
