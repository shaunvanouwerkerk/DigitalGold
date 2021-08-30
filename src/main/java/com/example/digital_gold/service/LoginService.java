package com.example.digital_gold.service;

/**
 * @Author Shaun van Ouwerkerk
 */

import com.example.digital_gold.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginService {

    private final HashService hashService;
    private RootRepository rootRepository;

    @Autowired
    public LoginService(HashService hashService, RootRepository rootRepository) {
        this.hashService = hashService;
        this.rootRepository = rootRepository;
    }

    public String login(String username, String password) {
        String token = null;
        String savedSalt = rootRepository.findCustomerSalt(username);
        String hashPassword = hashService.hash(password + savedSalt);
        String storedHash = rootRepository.findCustomerHashPassword(username);

        // als hash en stored hash overeen komen moet een token gegenereerd worden
        if(hashPassword.equals(storedHash)){
            token = UUID.randomUUID().toString();
            //todo token opslaan in database

//            tokenDatabase.insertUsernameWithHash(token, username); // DAO komen voor opslaan naam methode komt natuurlijk niet overeen
        }
        return token;
    }
}
