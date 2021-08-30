package com.example.digital_gold.service;
/**
 * @Author Shaun van Ouwerkerk
 */

import com.example.digital_gold.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatorService {
    private final HashService hashService;
    private RootRepository rootRepository;

    @Autowired
    public AuthenticatorService(HashService hashService, RootRepository rootRepository) {
        this.hashService = hashService;
        this.rootRepository = rootRepository;
    }

    public boolean authenticate(String username, String password) {
        String savedSalt = rootRepository.findCustomerSalt(username);
        String hashPassword = hashService.hash(password + savedSalt);
        String storedHash = rootRepository.findCustomerHashPassword(username);

        return hashPassword.equals(storedHash);
    }
    //Todo Nog afmaken Shaun
//    public boolean authenticate(String token) {
////        String username = rootRepository.findUsernameByToken(token);
//        // als er een user gevonden wordt is er natuurlijk ook een token
////        return username != null;
//
//    }
}
