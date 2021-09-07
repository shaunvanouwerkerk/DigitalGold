package com.example.digital_gold.service;
/**
 * @Author Shaun van Ouwerkerk
 */

import com.example.digital_gold.repository.MapDatabase;
import com.example.digital_gold.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatorService {
    private final HashService hashService;
    private RootRepository rootRepository;
    private MapDatabase tokenDatabase;

    @Autowired
    public AuthenticatorService(HashService hashService, RootRepository rootRepository, MapDatabase tokenDatabase) {
        this.hashService = hashService;
        this.rootRepository = rootRepository;
        this.tokenDatabase = tokenDatabase;
    }

    public boolean authenticate(String username, String password) {
        String savedSalt = rootRepository.findCustomerSalt(username);
        String hashPassword = hashService.hash(password + savedSalt);
        String storedHash = rootRepository.findCustomerHashPassword(username);

        return hashPassword.equals(storedHash);
    }

    public boolean authenticate(String token) {
        String username = tokenDatabase.findUserByToken(token);
        return username != null;

    }
}
