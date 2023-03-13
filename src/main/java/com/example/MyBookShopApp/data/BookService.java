package com.example.MyBookShopApp.data;
import com.example.MyBookShopApp.exceptions.ApiWrongParameterException;
import com.example.MyBookShopApp.model.components.PopularBooksAlgorithm;
import com.example.MyBookShopApp.model.dtos.RecommendedBooksDto;
import com.example.MyBookShopApp.model.entities.Author.AuthorEntity;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Book.BookRepository;
import com.example.MyBookShopApp.model.entities.Book2Author.Book2Author;
import com.example.MyBookShopApp.model.entities.Book2Author.Book2AuthorRepository;
import com.example.MyBookShopApp.model.entities.Book2User.Book2UserRepository;
import com.example.MyBookShopApp.model.entities.Rating.OverallRatingBook;
import com.example.MyBookShopApp.model.entities.Rating.OverallRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final OverallRatingRepository overallRatingRepository;
    private final Book2AuthorRepository book2AuthorRepository;
    private final Book2UserRepository book2UserRepository;
    private final PopularBooksAlgorithm popularBooksAlgorithm;
    @Autowired
    public BookService(BookRepository bookRepository,
                       Book2AuthorRepository book2AuthorRepository,
                       Book2UserRepository book2UserRepository,
                       PopularBooksAlgorithm popularBooksAlgorithm,
                       OverallRatingRepository overallRatingRepository) {
        this.bookRepository = bookRepository;
        this.book2AuthorRepository = book2AuthorRepository;
        this.book2UserRepository = book2UserRepository;
        this.popularBooksAlgorithm = popularBooksAlgorithm;
        this.overallRatingRepository = overallRatingRepository;
    }
    public List<Book2Author> getBooksData() {
        return book2AuthorRepository.findAll();
    }
    public List<Book2Author> getPopularBooks() {
        return book2AuthorRepository.findAllBy();
    }
    public List<AuthorEntity> getBooksByAuthor(String authorLastName) {
        return book2AuthorRepository.findBooksByAuthorLastName(authorLastName);
    }
    public List<BookEntity> getBooksByTitle(String title) throws ApiWrongParameterException {
        if (title.length() <= 1) {
            throw new ApiWrongParameterException("Wrong values passed to one or more parameters");
        } else {
            List<BookEntity> bookEntities = bookRepository.findBookEntitiesByTitleContaining(title);
            if (bookEntities.size() > 0) {
                return bookEntities;
            }
            throw  new ApiWrongParameterException("Not a single book found");
        }
    }
    public List<BookEntity> getBooksByPriceBetween(Integer min, Integer max) {
        return bookRepository.findBookEntitiesByPriceOldBetween(min, max);
    }
    public List<BookEntity> getBooksByPrice(Integer priceOld) {
        return bookRepository.findBookEntitiesByPriceOldIs(priceOld);
    }
    public List<BookEntity> getBestsellersBooks() {
        return bookRepository.findBookEntitiesByIsBestsellerTrue();
    }
    public List<BookEntity> getBooksByMaxDiscount() {
        return bookRepository.getBookEntitiesByMaxDiscount();
    }
    public Page<BookEntity> getRecommendedBooksPage(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }
    public Page<BookEntity> getRecentBooksForSlider(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findRecentBooks(LocalDateTime.now().minusMonths(1), nextPage);
    }
    public Object getPageOfRecentBooks(String from, String to, Integer offset, Integer limit, Model model) {
        Pageable nextPage;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyy");
        if (offset == null) {
            nextPage = PageRequest.of(0, 20);
            List<BookEntity> bookEntities = bookRepository.findRecentBooks(
                    LocalDateTime.now().minusMonths(1), LocalDateTime.now(), nextPage).getContent();
            model.addAttribute("recentBooksPage", bookEntities);
            return new ModelAndView("/books/recent");
        } if (from == null) {
            nextPage = PageRequest.of(offset, limit);
            List<BookEntity> bookEntities = bookRepository.findRecentBooks(
                    LocalDateTime.now().minusMonths(1), LocalDateTime.now(), nextPage).getContent();
            return new RecommendedBooksDto(bookEntities.size(), bookEntities);
        }
        nextPage = PageRequest.of(offset, limit);
        List<BookEntity> bookEntities = bookRepository.findRecentBooks(
                LocalDate.parse(from, formatter).atStartOfDay(),
                LocalDate.parse(to, formatter).atStartOfDay(), nextPage).getContent();
        return new RecommendedBooksDto(bookEntities.size(), bookEntities);
    }
    public Page<BookEntity> getPopularBooksPage(Integer offset, Integer limit) {
        List<BookEntity> bookEntities = popularBooksAlgorithm.getPopularBooks();
        List<BookEntity> list;
        int startItem = offset * limit;
        if (bookEntities.size() < offset) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + limit, bookEntities.size());
            list = bookEntities.subList(startItem, toIndex);
        }
        return new PageImpl<>(list, PageRequest.of(offset, limit), list.size());
    }
    public Page<BookEntity> getPageOfRecommendedBooks(String searchWord, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBookEntitiesByTitleContaining(searchWord, nextPage);
    }
    public BookEntity getBook(String slug) {
        Optional<BookEntity> book = bookRepository.findBySlugIs(slug);
        return book.orElse(null);
    }
    public List<BookEntity> getBooksBySlugList(List<String> booksSlutList) {
        return bookRepository.findAllBySlugIsIn(booksSlutList);
    }

    public boolean rateBook (String bookSlug, Integer value) {
        Optional<BookEntity> book = bookRepository.findBySlugIs(bookSlug);
        if (book.isPresent()) {
            OverallRatingBook rating;
            if (book.get().getRating() == null) {
               rating = new OverallRatingBook();
               rating.setOneStarRating(0);
               rating.setTwoStarRating(0);
               rating.setThreeStarRating(0);
               rating.setFourStarRating(0);
               rating.setFiveStarRating(0);
               book.get().setRating(rating);
            }

            switch (value) {
                case 1 -> book.get().getRating().setOneStarRating(book.get().getRating().getOneStarRating() + 1);
                case 2 -> book.get().getRating().setTwoStarRating(book.get().getRating().getTwoStarRating() + 1);
                case 3 -> book.get().getRating().setThreeStarRating(book.get().getRating().getThreeStarRating() + 1);
                case 4 -> book.get().getRating().setFourStarRating(book.get().getRating().getFourStarRating() + 1);
                case 5 -> book.get().getRating().setFiveStarRating(book.get().getRating().getFiveStarRating() + 1);
            }
            bookRepository.save(book.get());
            return true;

        }
        return false;
    }
}
