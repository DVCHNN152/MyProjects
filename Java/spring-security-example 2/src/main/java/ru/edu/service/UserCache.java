package ru.edu.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Component
public class UserCache {

    private Map<String, UserInfo> cache = new HashMap<>();

    public UserInfo get(String id){
        return cache.get(id);
    }

    private void create(UserInfo info){
        cache.put(info.getLogin(), info);
    }

    @PostConstruct
    public void init(){
        create(new UserInfo("anton777", "qwerty", "AntonT","admin"));
        create(new UserInfo("dima888", "123", "DimaCh","admin"));
        create(new UserInfo("denis", "qwerty123", "DenisS","manager"));
    }

}
