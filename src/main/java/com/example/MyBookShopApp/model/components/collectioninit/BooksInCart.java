package com.example.MyBookShopApp.model.components.collectioninit;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BooksInCart extends ArrayList<BookEntity> implements BooksCollection {
}
