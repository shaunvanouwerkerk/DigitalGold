package com.example.digital_gold.repository;
/**
 * @Author Shaun van Ouwerkerk
 */

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MapDatabase {

    private Map<String, String> database;

    public MapDatabase() {
        database = new ConcurrentHashMap<>();
    }

    public String findUserByToken(String token) {
        return database.get(token);
    }

    //Methode die database vult en controleert op dubbele
    public boolean insertTokenWithHash(String token, String username){
        if(!database.containsKey(token)){
            database.put(token,username);
            return true;
        }
        return false;
    }
}

