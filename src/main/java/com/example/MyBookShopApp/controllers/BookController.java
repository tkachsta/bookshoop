package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.BookService;
import com.example.MyBookShopApp.data.ResourceStorage;
import com.example.MyBookShopApp.data.ReviewService;
import com.example.MyBookShopApp.data.TagService;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Book.BookRepository;
import com.example.MyBookShopApp.model.entities.Book2Tag.Book2Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/books")
public class BookController extends AbstractHeaderController {
    private final BookService bookService;
    private final ReviewService reviewService;
    private final TagService tagService;
    private final ResourceStorage storage;
    private final BookRepository bookRepository;
    @Autowired
    public BookController(BookService bookService,
                          ReviewService reviewService, TagService tagService,
                          ResourceStorage storage,
                          BookRepository bookRepository) {
        this.bookService = bookService;
        this.reviewService = reviewService;
        this.tagService = tagService;
        this.storage = storage;
        this.bookRepository = bookRepository;
    }



    @ModelAttribute("currentBook")
    public BookEntity getCurrentBook() {
        return new BookEntity();
    }
    @ModelAttribute("bookTags")
    public List<Book2Tag> getBookTags() {
        return new ArrayList<>();
    }



    @GetMapping(value = {"/{slug}","/books/{slug}"})
    @ResponseBody
    public ModelAndView getBookPage(@PathVariable String slug, Model model) {

        BookEntity book =  bookService.getBook(slug);
        model.addAttribute("currentBook",book);
        model.addAttribute("bookTags", tagService.getTagsByBook(slug));
        model.addAttribute("bookReviews", reviewService.getBookReviews(book));
        return new ModelAndView("/books/slug");

    }
    @PostMapping("/{slug}/img/save")
    public String saveNewBookImage(@RequestParam("file") MultipartFile file,
                                   @PathVariable("slug") String slug) throws IOException {

        String savePath = storage.saveNewBookImage(file, slug);
        Optional<BookEntity> bookToUpdate = bookRepository.findBySlugIs(slug);
        if (bookToUpdate.isPresent()) {
            BookEntity book = bookToUpdate.get();
            book.setImage(savePath);
            bookRepository.save(book);
        }

        return ("redirect:/books/" + slug);
    }
    @GetMapping("/download/{hash}")
    public ResponseEntity<ByteArrayResource> bookFile(@PathVariable("hash") String hash) throws IOException {

        Path path = storage.getBookFilePath(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file path: " + path);

        MediaType mediaType = storage.getBookFileMime(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file mime type: " + mediaType.getType());

        byte[] data = storage.getBookFileByteArray(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file data length: " + data.length);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(new ByteArrayResource(data));




    }

}
