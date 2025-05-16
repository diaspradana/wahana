package com.example.wahana.service;

import com.example.wahana.model.User;
import com.example.wahana.service.UserService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User("admin", "admin123", "admin"));
        users.add(new User("user", "user123", "user"));
    }

    public User login(String username, String password) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
