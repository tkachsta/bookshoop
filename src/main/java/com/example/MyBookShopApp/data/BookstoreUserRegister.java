package com.example.MyBookShopApp.data;
import com.example.MyBookShopApp.model.dtos.security.ContactConfirmationPayload;
import com.example.MyBookShopApp.model.dtos.security.RegistrationForm;
import com.example.MyBookShopApp.model.entities.Users.UserEntity;
import com.example.MyBookShopApp.model.entities.Users.UserRepository;
import com.example.MyBookShopApp.model.response.ContactConfirmationResponse;
import com.example.MyBookShopApp.security.BookStoreUserDetailService;
import com.example.MyBookShopApp.security.BookStoreUserDetails;
import com.example.MyBookShopApp.security.jwt.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BookstoreUserRegister {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final BookStoreUserDetailService userDetailService;
    private final JWTUtil jwtUtil;

    public void registerNewUser(RegistrationForm registrationForm) {

        if(userRepository.findByEmailIs(registrationForm.getEmail()) == null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setBalance(0);
            userEntity.setName(registrationForm.getName());
            userEntity.setEmail(registrationForm.getEmail());
            userEntity.setPhone(registrationForm.getPhone());
            userEntity.setPassword(passwordEncoder.encode(registrationForm.getPass()));
            userEntity.setRegDate(Timestamp.valueOf(LocalDateTime.now()));
            userRepository.saveAndFlush(userEntity);
        }



    }

    public ContactConfirmationResponse login(ContactConfirmationPayload payload) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        payload.getContact(), payload.getCode()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult("true");
        return response;
    }

    public ContactConfirmationResponse jwtLogin(ContactConfirmationPayload payload) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getContact(),
                payload.getCode()));
        BookStoreUserDetails userDetails =
                (BookStoreUserDetails) userDetailService.loadUserByUsername(payload.getContact());
        String jwtToken = jwtUtil.generateToken(userDetails);
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult(jwtToken);
        return response;

    }

    public UserEntity getCurrentUser() {
        BookStoreUserDetails userDetails =
                (BookStoreUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUserEntity();
    }
}
