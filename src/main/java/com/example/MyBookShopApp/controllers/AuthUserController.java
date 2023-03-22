package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BookstoreUserRegister;
import com.example.MyBookShopApp.data.booklifecycle.StatusHub;
import com.example.MyBookShopApp.data.mapper.MapperToBookDto;
import com.example.MyBookShopApp.model.dtos.security.ContactConfirmationPayload;
import com.example.MyBookShopApp.model.dtos.security.RegistrationForm;
import com.example.MyBookShopApp.model.entities.Book.BookEntity;
import com.example.MyBookShopApp.model.response.ContactConfirmationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class AuthUserController extends AbstractHeaderController {
    private final BookstoreUserRegister bookstoreUserRegister;
    private final MapperToBookDto mapperToBookDto;
    private final StatusHub statusHub;
    @Autowired
    public AuthUserController(BookstoreUserRegister bookstoreUserRegister,
                              MapperToBookDto mapperToBookDto,
                              StatusHub statusHub) {
        this.bookstoreUserRegister = bookstoreUserRegister;
        this.mapperToBookDto = mapperToBookDto;
        this.statusHub = statusHub;
    }
    @GetMapping("/signin")
    public ModelAndView signingPage() {
        return new ModelAndView("signin");
    }
    @GetMapping("/signup")
    public ModelAndView handleSignUp(Model model) {
        model.addAttribute("regForm", new RegistrationForm());
        return new ModelAndView("signup");
    }
    @PostMapping("/requestContactConfirmation")
    @ResponseBody
    public ContactConfirmationResponse handleRequestContactConfirmation(@RequestBody ContactConfirmationPayload payload) {

        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult("true");
        return response;

    }
    @PostMapping("/approveContact")
    @ResponseBody
    public ContactConfirmationResponse handleApproveContact(@RequestBody ContactConfirmationPayload payload) {

        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult("true");
        return response;

    }
    @PostMapping("/reg")
    public ModelAndView handleUserRegistration(RegistrationForm registrationForm, Model model) {

        bookstoreUserRegister.registerNewUser(registrationForm);
        model.addAttribute("regOk", true);
        return new ModelAndView("signin");

    }
    @PostMapping("/login")
    @ResponseBody
    ContactConfirmationResponse handleLogin(@RequestBody ContactConfirmationPayload payload, HttpServletResponse response) {
        ContactConfirmationResponse loginResponse = bookstoreUserRegister.jwtLogin(payload);
        Cookie cookie = new Cookie("token", loginResponse.getResult());
        response.addCookie(cookie);
        return loginResponse;
    }
    @GetMapping("/my")
    public ModelAndView handleMy(Model model) {
        List<BookEntity> bookEntities = statusHub.getBoughtStatus().getStatusCollection();
        model.addAttribute("myBooksPage", bookEntities.stream().map(mapperToBookDto::convertToDto).toList());
        return new ModelAndView("my");
    }
    @GetMapping("/myarchive")
    public ModelAndView handleMyArchive(Model model) {
        List<BookEntity> bookEntities = statusHub.getArchivedStatus().getStatusCollection();
        model.addAttribute("myBooksPage", bookEntities.stream().map(mapperToBookDto::convertToDto).toList());
        return new ModelAndView("myarchive");
    }
    @GetMapping("/profile")
    public ModelAndView handleProfile(Model model) {
        model.addAttribute("currentUser", bookstoreUserRegister.getCurrentUser());
        return new ModelAndView("profile");
    }

//    @GetMapping("/logout")
//    public ModelAndView handleLogOut(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        SecurityContextHolder.clearContext();
//        if(session != null) {
//            session.invalidate();
//        }
//
//        for (Cookie cookie : request.getCookies()) {
//            cookie.setMaxAge(0);
//        }
//
//        return new ModelAndView("redirect:/");
//
//    }

}
