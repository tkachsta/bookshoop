package com.example.MyBookShopApp.data;
import com.example.MyBookShopApp.model.components.AuthorBiographyRendering;
import com.example.MyBookShopApp.model.entities.Author.AuthorEntity;
import com.example.MyBookShopApp.model.entities.Author.AuthorRepository;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Book2Author.Book2AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.activation.DataContentHandler;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final Book2AuthorRepository book2AuthorRepository;
    private final AuthorRepository authorRepository;
    private final AuthorBiographyRendering biographyRendering;

    @Autowired
    public AuthorService(Book2AuthorRepository book2AuthorRepository,
                         AuthorRepository authorRepository,
                         AuthorBiographyRendering biographyRendering) {
        this.book2AuthorRepository = book2AuthorRepository;
        this.authorRepository = authorRepository;
        this.biographyRendering = biographyRendering;
    }

    public Map<String, List<AuthorEntity>> getAuthorsMap() {
        List<AuthorEntity> authors = book2AuthorRepository.findDistinctAuthors();
        return authors.stream()
                .collect(Collectors.groupingBy((AuthorEntity a) -> a.getLastName().substring(0,1)));
    }
    public Page<BookEntity> getAuthorBooks(Integer offset, Integer limit, String slug) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return book2AuthorRepository.findBooksByAuthorSlug(slug, nextPage);
    }
    public AuthorEntity getAuthor(String slug) {
        Optional<AuthorEntity> author = authorRepository.findAllBySlugIs(slug);
        return author.orElse(null);
    }
    public List<String> getShortDescription(String description) {
        biographyRendering.setDescriptions(description);
        List<String> shortDesc = biographyRendering.getShortDescription();
        return shortDesc;
    }
    public List<String> getLongDescription() {
        return biographyRendering.getLongDescription();
    }

}
