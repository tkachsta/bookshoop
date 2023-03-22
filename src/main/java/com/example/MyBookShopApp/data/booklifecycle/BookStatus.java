package com.example.MyBookShopApp.data.booklifecycle;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import java.util.List;

public interface BookStatus {

    public List<BookEntity> getStatusCollection();
    public String getCurrentStatus();


}
