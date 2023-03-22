package com.example.MyBookShopApp.data;
import com.example.MyBookShopApp.model.entities.Users.UserEntity;
import com.example.MyBookShopApp.security.BookStoreUserDetails;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class AbstractAuthorityService {
    @ModelAttribute("isAuthenticated")
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken);
    }

    @ModelAttribute("currentUser")
    public UserEntity getCurrentUser() {
        if (isAuthenticated()) {
            BookStoreUserDetails bookStoreUserDetails =
                    (BookStoreUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return bookStoreUserDetails.getUserEntity();
        }
        return null;
    }

}
