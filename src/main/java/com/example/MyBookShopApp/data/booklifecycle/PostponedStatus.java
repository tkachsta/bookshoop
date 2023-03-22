package com.example.MyBookShopApp.data.booklifecycle;
import com.example.MyBookShopApp.model.components.collectioninit.BooksPostponed;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PostponedStatus implements BookStatus {
    @Value("${cart-model.statuses.kept}")
    private String keptStatus;
    private final BooksPostponed booksPostponed;
    @Autowired
    public PostponedStatus(BooksPostponed booksPostponed) {
        this.booksPostponed = booksPostponed;
    }
    @Override
    public List<BookEntity> getStatusCollection() {
        return booksPostponed;
    }
    @Override
    public String getCurrentStatus() {
        return this.keptStatus;
    }

}
