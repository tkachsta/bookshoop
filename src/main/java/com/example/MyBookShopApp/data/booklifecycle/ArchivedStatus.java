package com.example.MyBookShopApp.data.booklifecycle;
import com.example.MyBookShopApp.model.components.collectioninit.BooksArchived;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ArchivedStatus implements BookStatus {

    @Value("${cart-model.statuses.archived}")
    private String archivedStatus;
    private final BooksArchived booksArchived;
    @Autowired
    public ArchivedStatus(BooksArchived booksArchived) {
        this.booksArchived = booksArchived;
    }
    public List<BookEntity> getStatusCollection() {
        return booksArchived;
    }
    public String getCurrentStatus() {
        return this.archivedStatus;
    }


}
