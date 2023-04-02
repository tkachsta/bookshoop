package com.example.MyBookShopApp.data;
import com.example.MyBookShopApp.model.entities.JWT.JwtBlackList;
import com.example.MyBookShopApp.model.entities.JWT.JwtBlackListRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Getter
public class JwtBlackListService {

    private final JwtBlackListRepository blackListRepository;
    public boolean containsToken(String token) {
        Optional<JwtBlackList> jwtToken = blackListRepository.findByToken(token);
        return jwtToken.isPresent();
    }
    public void addTokenToBlackList(String token) {
        JwtBlackList jwtToken = new JwtBlackList();
        jwtToken.setToken(String.valueOf(token.hashCode()));
        blackListRepository.save(jwtToken);
    }


}
