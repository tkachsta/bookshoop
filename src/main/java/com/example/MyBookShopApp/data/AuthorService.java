package com.example.MyBookShopApp.data;
import com.example.MyBookShopApp.model.Author.AuthorEntity;
import com.example.MyBookShopApp.model.Book2Author.Book2Author;
import com.example.MyBookShopApp.model.Book2Author.Book2AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final Book2AuthorRepository book2AuthorRepository;

    @Autowired
    public AuthorService(Book2AuthorRepository book2AuthorRepository) {
        this.book2AuthorRepository = book2AuthorRepository;
    }


    public Map<String, List<AuthorEntity>> getAuthorsMap() {
        List<AuthorEntity> authors = book2AuthorRepository.findDistinctAuthors();
        return authors.stream()
                .collect(Collectors.groupingBy((AuthorEntity a) -> a.getLastName().substring(0,1)));
    }
}
