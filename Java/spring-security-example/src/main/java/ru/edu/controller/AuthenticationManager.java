package ru.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.edu.service.UserCache;
import ru.edu.service.UserInfo;

@Component
public class AuthenticationManager {

    private UserCache userCache;

    public AuthResult authorize(String login, String password) {
        UserInfo user = userCache.get(login);
        if (user == null){
            return new AuthResult("User not found. Please register");
        }

        if (!user.getPassword().equals(password)){
            return new AuthResult("User not found. Please register");
        }

        if (!"admin".equals(user.getRole())){
            return  new AuthResult("Access Denied!");
        }
        return  new AuthResult("OK");

    }


    @Autowired
    public void setUserCache(UserCache userCache) {
        this.userCache = userCache;
    }
}
