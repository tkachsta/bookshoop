package com.example.MyBookShopApp.data;
import com.example.MyBookShopApp.controllers.AbstractHeaderController;
import com.example.MyBookShopApp.data.booklifecycle.BookStatus;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LinkBook2UserService extends AbstractHeaderController {

    Logger log = LoggerFactory.getLogger(LinkBook2UserService.class);

    private final BookService bookService;
    private final UserService userService;
    public LinkBook2UserService(BookService bookService,
                                UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }


    public boolean linkBookWithUser(String slug, BookStatus fromStatus, BookStatus toStatus) {

        List<String> to = toStatus.getStatusCollection().stream().map(BookEntity::getSlug).toList();
        if (!isAuthenticated()) {
            if (to.isEmpty() || !to.contains(slug)) {
                fromStatus.getStatusCollection().removeIf(book -> book.getSlug().equals(slug));
                toStatus.getStatusCollection().add(bookService.getBook(slug));
                return true;
            }
        } else {
            return userService.linkBook2User(slug, toStatus.getCurrentStatus());
        }
        return false;
    }
    public boolean unlinkBookWithUser(String slug, BookStatus fromStatus) {

        List<String> from = fromStatus.getStatusCollection().stream().map(BookEntity::getSlug).toList();
        if (!isAuthenticated()) {
            if (!from.isEmpty() && from.contains(slug)) {
                log.info(slug);
                fromStatus.getStatusCollection().removeIf(book -> book.getSlug().equals(slug));
                return true;
            }
        } else {
            fromStatus.getStatusCollection().removeIf(book -> book.getSlug().equals(slug));
            return userService.unlinkBookFromUser(slug);
        }
        return false;


    }
    public boolean bulkBooksTransfer(String slug, BookStatus fromStatus, BookStatus toStatus) {
        String[] booksToTransfer = slug.split(",");
        if (booksToTransfer.length != 0) {
            for (String s: booksToTransfer) {
                linkBookWithUser(s, fromStatus, toStatus);
            }
            return true;
        }
        return false;
    }


}
