package com.example.MyBookShopApp.data;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Book.BookRepository;
import com.example.MyBookShopApp.model.entities.Book2User.*;
import com.example.MyBookShopApp.model.entities.Users.UserEntity;
import com.example.MyBookShopApp.model.entities.Users.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService extends AbstractAuthorityService {
    private final Book2UserRepository book2UserRepository;
    private final Book2UserTypeRepository book2UserTypeRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    public UserService(Book2UserRepository book2UserRepository,
                       Book2UserTypeRepository book2UserTypeRepository,
                       BookRepository bookRepository,
                       UserRepository userRepository) {
        this.book2UserRepository = book2UserRepository;
        this.book2UserTypeRepository = book2UserTypeRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public boolean linkBook2User(String slug, String linkType) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepository.findByEmailIs(auth.getName());
        Optional<BookEntity> book = bookRepository.findBySlugIs(slug);
        Optional<Book2User> B2U = book2UserRepository.findByBook2UserKey(new Book2UserKey(user, book.get()));
        Book2UserType B2UT = book2UserTypeRepository.findAllByCodeIs(linkType);

        Book2User book2User;
        if (B2U.isEmpty()) {
            book2User = new Book2User();
            Book2UserKey book2UserKey = new Book2UserKey(user, book.get());
            book2User.setBook2UserKey(book2UserKey);
            book2User.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        } else {
            book2User = B2U.get();
        }
        book2User.setBook2UserType(B2UT);
        book2UserRepository.save(book2User);
        return true;
    }
    public String typeOfBookLink(String slug) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepository.findByEmailIs(auth.getName());
        Optional<BookEntity> book = bookRepository.findBySlugIs(slug);
        Optional<Book2User> book2User = book2UserRepository.findByBook2UserKey(new Book2UserKey(user, book.get()));
        if (book2User.isPresent()) {
            return book2User.get().getBook2UserType().getCode();
        } else {
            return "NONE";
        }
    }
    public List<BookEntity> getBooksByUserAndStatus(String status) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepository.findByEmailIs(auth.getName());
        return book2UserRepository.findBooksByUserAndStatus(user, status);
    }
    public boolean unlinkBookFromUser(String slug) {
        Optional<BookEntity> book = bookRepository.findBySlugIs(slug);
        if (book.isPresent()) {
            UserEntity user = getCurrentUser();
            book2UserRepository.deleteByKey(new Book2UserKey(user, book.get()));
            return true;
        }
        return false;
    }

}
