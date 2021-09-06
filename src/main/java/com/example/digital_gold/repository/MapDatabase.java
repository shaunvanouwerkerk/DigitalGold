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

    public String findHashByUsername(String username) {
        return database.get(username);
    }

    //Methode die database vult en controleert op dubbele
    public boolean insertUsernameWithHash(String username, String hash){
        if(!database.containsKey(username)){
            database.put(username, hash);
            return true;
        }
        return false;
    }
}

