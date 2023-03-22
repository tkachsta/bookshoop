package com.example.MyBookShopApp.data.booklifecycle;
import com.example.MyBookShopApp.model.components.collectioninit.BooksBought;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class BoughtStatus implements BookStatus{

    @Value("${cart-model.statuses.paid}")
    private String paidStatus;
    private final BooksBought booksBought;
    @Autowired
    public BoughtStatus(BooksBought booksBought) {
        this.booksBought = booksBought;
    }
    public List<BookEntity> getStatusCollection() {
        return booksBought;
    }
    public String getCurrentStatus() {
        return this.paidStatus;
    }



}
