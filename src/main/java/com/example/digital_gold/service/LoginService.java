package com.example.digital_gold.service;

/**
 * @Author Shaun van Ouwerkerk
 */

import com.example.digital_gold.repository.MapDatabase;
import com.example.digital_gold.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class LoginService {

    private final HashService hashService;
    private RootRepository rootRepository;
    private MapDatabase tokenDatabase;

    @Autowired
    public LoginService(HashService hashService, RootRepository rootRepository, MapDatabase tokenDatabase) {
        this.hashService = hashService;
        this.rootRepository = rootRepository;
        this.tokenDatabase = tokenDatabase;
    }

    public String login(String username, String password) {
        String token = null;
        String savedSalt = rootRepository.findCustomerSalt(username);
        String hashPassword = hashService.hash(password + savedSalt);
        String storedHash = rootRepository.findCustomerHashPassword(username);

        // Als de inlog slaagt omdat de hash en stored hash overeenkomen moet een token gegenereerd worden
        if(hashPassword.equals(storedHash)){
            token = UUID.randomUUID().toString();
            //Methode om token op te slaan en te checken of de token niet al bestaat
            tokenDatabase.insertUsernameWithHash(username,token);
        }
        return token;
    }
    public String loginAdministrator(String username, String password) {
        String token = null;
        String savedSalt = rootRepository.findAdministratorSalt(username);
        String hashPassword = hashService.hash(password + savedSalt);
        String storedHash = rootRepository.findAdministratorHashPassword(username);

        // als hash en stored hash overeen komen moet een token gegenereerd worden
        if(hashPassword.equals(storedHash)){
            token = UUID.randomUUID().toString();
            //Methode om token op te slaan en te checken op dubbele
            tokenDatabase.insertUsernameWithHash(username,token);
        }
        return token;
    }


}
