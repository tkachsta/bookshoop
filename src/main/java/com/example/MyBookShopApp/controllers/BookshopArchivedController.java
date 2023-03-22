package com.example.MyBookShopApp.controllers;
import com.example.MyBookShopApp.data.LinkBook2UserService;
import com.example.MyBookShopApp.data.booklifecycle.ArchivedStatus;
import com.example.MyBookShopApp.data.booklifecycle.BoughtStatus;
import com.example.MyBookShopApp.model.response.FalseResponse;
import com.example.MyBookShopApp.model.response.TrueResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/archived")
@AllArgsConstructor
public class BookshopArchivedController {
    private final LinkBook2UserService linkBook2UserService;
    private final BoughtStatus boughtStatus;
    private final ArchivedStatus archivedStatus;

    @RequestMapping("/toarchive/changeBookStatus/{slug}")
    public ResponseEntity<?> transferBookToArchive(@PathVariable("slug") String slug) {

        if(linkBook2UserService.linkBookWithUser(slug, boughtStatus, archivedStatus)) {
            return new ResponseEntity<>(new TrueResponse(true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new FalseResponse(false, "Не удалось переместить книгу"),
                HttpStatus.BAD_REQUEST);

    }

    @RequestMapping("/fromarchive/changeBookStatus/{slug}")
    public ResponseEntity<?> transferFromArchive(@PathVariable("slug") String slug) {

        if(linkBook2UserService.linkBookWithUser(slug, archivedStatus, boughtStatus)) {
            return new ResponseEntity<>(new TrueResponse(true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new FalseResponse(false, "Не удалось переместить книгу"),
                HttpStatus.BAD_REQUEST);

    }


}
