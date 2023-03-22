package com.example.MyBookShopApp.model.components.collectioninit;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BooksCollectionHub {

    private final BooksInCart booksInCart;
    private final BooksPostponed booksPostponed;
    private final BooksBought booksBought;
    private final BooksArchived booksArchived;

    public BooksCollectionHub(BooksInCart booksInCart,
                              BooksPostponed booksPostponed,
                              BooksBought booksBought,
                              BooksArchived booksArchived) {
        this.booksInCart = booksInCart;
        this.booksPostponed = booksPostponed;
        this.booksBought = booksBought;
        this.booksArchived = booksArchived;
    }

}
