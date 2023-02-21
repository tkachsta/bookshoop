package com.example.MyBookShopApp.data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksData() {
        List<Book> books = jdbcTemplate.query("SELECT b.`id`, a.`first_name`, a.`last_name`, b.`title`, b.`priceold`, b.`price`, b.`discount`, b.`is_bestseller`\n" +
                "FROM BOOKS b JOIN `authors` a ON a.`id_author` = b.`id_author` ", (ResultSet rs, int rowNum) -> {
            Author author = new Author();
            author.setName(rs.getString("first_name"));
            author.setSurname(rs.getString("last_name"));

            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getString("priceOld"));
            book.setPrice(rs.getString("price"));
            book.setDiscount(rs.getInt("discount"));
            book.setBestseller(rs.getBoolean("is_bestseller"));
            book.setAuthor(author);
            return book;
        });
        return new ArrayList<>(books);
    }
}
