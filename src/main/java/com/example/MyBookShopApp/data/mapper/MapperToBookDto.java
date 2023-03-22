package com.example.MyBookShopApp.data.mapper;
import com.example.MyBookShopApp.data.booklifecycle.StatusHub;
import com.example.MyBookShopApp.model.dtos.BookDto;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperToBookDto {
    private final ModelMapper modelMapper;
    private final StatusHub statusHub;
    public MapperToBookDto(ModelMapper modelMapper,
                           StatusHub statusHub) {
        this.modelMapper = modelMapper;
        this.statusHub = statusHub;
    }

    public BookDto convertToDto(BookEntity book) {
        BookDto bookDto = modelMapper.map(book, BookDto.class);

        bookDto.setDescription(book.getDescription());
        bookDto.setAuthors(book.getAuthors());
        bookDto.setImage(book.getImage());
        bookDto.setBestseller(book.isBestseller());
        bookDto.setPrice(book.getPrice());
        bookDto.setPriceOld(book.getPriceOld());
        bookDto.setPub_date(book.getPub_date());
        bookDto.setSlug(book.getSlug());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthors(book.getAuthors());
        bookDto.setStatus(defineStatus(book.getSlug()));

        return bookDto;

    }
    private String defineStatus(String slug) {
       return statusHub.getBookCurrentStatus(slug).getCurrentStatus();
    }




}
