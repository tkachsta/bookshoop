package com.example.MyBookShopApp.data;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Book2Genre.Book2GenreRepository;
import com.example.MyBookShopApp.model.entities.Genre.GenreEntity;
import com.example.MyBookShopApp.model.entities.Genre.GenreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.activation.DataContentHandler;
import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;
    private final Book2GenreRepository book2GenreRepository;

    public GenreService(GenreRepository genreRepository,
                        Book2GenreRepository book2GenreRepository) {
        this.genreRepository = genreRepository;
        this.book2GenreRepository = book2GenreRepository;
    }
    public List<GenreEntity> getParentGenres() {
        List<GenreEntity> genreEntities = genreRepository.findAllByParentIdIsNull();
        return genreEntities;
    }

    public Page<BookEntity> getBooksByGenre(Integer offset, Integer limit, String genre) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return book2GenreRepository.findBooksByGenre(genre, nextPage);
    }
}
