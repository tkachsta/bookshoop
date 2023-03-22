package com.example.MyBookShopApp.security;
import com.example.MyBookShopApp.model.entities.Users.UserEntity;
import com.example.MyBookShopApp.model.entities.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookStoreUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public BookStoreUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity bookstoreUser = userRepository.findByEmailIs(username);
        if (bookstoreUser != null) {
            return new BookStoreUserDetails(bookstoreUser);
        } throw  new UsernameNotFoundException("user not found!");
    }
}
