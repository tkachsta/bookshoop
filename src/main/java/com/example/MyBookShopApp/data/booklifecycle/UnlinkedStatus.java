package com.example.MyBookShopApp.data.booklifecycle;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UnlinkedStatus implements BookStatus{

    @Value("${cart-model.statuses.unlinked}")
    private String unlinkedStatus;

    @Override
    public List<BookEntity> getStatusCollection() {
        return new ArrayList<>();
    }
    @Override
    public String getCurrentStatus() {
        return this.unlinkedStatus;
    }



}
