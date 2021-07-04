package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.model.Provider;
import com.java.model.User;
import com.java.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
     
    public void processOAuthPostLogin(String email) {
    	System.out.println("username: " + email);
        User existUser = repo.getUserByEmail(email);
         
        if (existUser == null) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setProvider(Provider.FACEBOOK);
            newUser.setEnabled(true);          
             
            repo.save(newUser);        
        }
         
    }
}
