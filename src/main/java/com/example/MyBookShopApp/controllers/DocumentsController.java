package com.example.MyBookShopApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documents")
public class DocumentsController {


    @GetMapping
    public String documentsPage() {
        return "documents/index";
    }
    @GetMapping("/slug")
    public String documentSlugPage() {
        return "documents/slug";
    }

}
