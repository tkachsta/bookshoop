package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.*;
import com.example.MyBookShopApp.data.booklifecycle.StatusHub;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.entities.Book.BookRepository;
import com.example.MyBookShopApp.model.entities.Book2Tag.Book2Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

@Controller
@RequestMapping("/books")
public class BookController extends AbstractHeaderController {
    private final BookService bookService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final TagService tagService;
    private final ResourceStorage storage;
    private final BookRepository bookRepository;
    private final StatusHub statusHub;


    Logger log = LoggerFactory.getLogger(AbstractHeaderController.class);


    @Autowired
    public BookController(BookService bookService,
                          ReviewService reviewService, TagService tagService,
                          ResourceStorage storage,
                          BookRepository bookRepository, UserService userService, StatusHub statusHub) {
        this.bookService = bookService;
        this.reviewService = reviewService;
        this.tagService = tagService;
        this.storage = storage;
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.statusHub = statusHub;
    }



    @ModelAttribute("currentBook")
    public BookEntity getCurrentBook() {
        return new BookEntity();
    }
    @ModelAttribute("bookTags")
    public List<Book2Tag> getBookTags() {
        return new ArrayList<>();
    }



    @GetMapping(value = "/{slug}")
    @ResponseBody
    public ModelAndView getBookPage(@PathVariable String slug, Model model) {

        String status = statusHub.getBookCurrentStatus(slug).getCurrentStatus();
        log.info(status);



        BookEntity book =  bookService.getBook(slug);
        model.addAttribute("currentBook",book);
        model.addAttribute("bookTags", tagService.getTagsByBook(slug));
        model.addAttribute("bookReviews", reviewService.getBookReviews(book));
        model.addAttribute("typeOfBookLink", status);
        model.addAttribute("bookIsBought", statusHub.bookIsBought(slug));

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
        MediaType mediaType = storage.getBookFileMime(hash);
        byte[] data = storage.getBookFileByteArray(hash);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(new ByteArrayResource(data));




    }

}
