package com.example.MyBookShopApp.model.components;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Book2User.Book2User;
import com.example.MyBookShopApp.model.entities.Book2User.Book2UserRepository;
import com.example.MyBookShopApp.model.entities.Book2User.Book2UserType;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
@Getter
public class PopularBooksAlgorithm {
    private final Book2UserRepository book2UserRepository;
    private final List<BookEntity> popularBooks;
    @Autowired
    public PopularBooksAlgorithm(Book2UserRepository book2UserRepository) {
        this.book2UserRepository = book2UserRepository;
        this.popularBooks = setPopularBooks();

    }
    public List<BookEntity> setPopularBooks() {
        List<Book2User> book2UserList = book2UserRepository.getBook2UserByType();
        Map<BookEntity, List<Book2User>> map = new HashMap<>();
        for (Book2User B2U : book2UserList) {
            BookEntity bookEntity = B2U.getBook();
            List<Book2User> book2Users;
            if (!map.containsKey(bookEntity)) {
                book2Users = new ArrayList<>();
            } else {
                book2Users = map.get(bookEntity);
            }
            book2Users.add(B2U);
            map.put(bookEntity, book2Users);
        }

        List<BookEntity> bookEntities = new ArrayList<>(map.entrySet()
                .stream().sorted(Comparator.comparing(k -> {
                    float rating = 0;
                    List<Book2User> userEntityList = k.getValue();
                    for (Book2User B2U : userEntityList) {
                        Book2UserType B2UT = B2U.getBook2UserType();
                        if (B2UT.getCode().equals("PAID")) {
                            rating += 1;
                        } else if (B2UT.getCode().equals("CART")) {
                            rating += 0.7;
                        } else if (B2UT.getCode().equals("KEPT")) {
                            rating += 0.4;
                        }
                    }
                    return rating;
                })).map(Map.Entry::getKey).toList());
        Collections.reverse(bookEntities);
        return bookEntities;
    }








}
