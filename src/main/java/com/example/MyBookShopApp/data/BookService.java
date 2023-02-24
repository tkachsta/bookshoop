package com.example.MyBookShopApp.data;
import com.example.MyBookShopApp.model.Book.BookEntity;
import com.example.MyBookShopApp.model.Book.BookRepository;
import com.example.MyBookShopApp.model.Book2Author.Book2Author;
import com.example.MyBookShopApp.model.Book2Author.Book2AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final Book2AuthorRepository book2AuthorRepository;

    @Autowired
    public BookService(BookRepository bookRepository,
                       Book2AuthorRepository book2AuthorRepository) {
        this.bookRepository = bookRepository;
        this.book2AuthorRepository = book2AuthorRepository;
    }
    public List<Book2Author> getBooksData() {
        return book2AuthorRepository.findAll();
    }
    public List<Book2Author> getPopularBooks() {
        return book2AuthorRepository.findAllBy();
    }

}
