package com.example.wahana.model.service;

import com.example.wahana.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    private final List<User> users = new ArrayList<>();

    public AuthService() {
        users.add(new User("admin", "admin123", "admin"));
        users.add(new User("user", "user123", "user"));
    }

    public User authenticate(String username, String password) {
        return users.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .filter(u -> u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public boolean isAdmin(User user) {
        return user != null && "admin".equalsIgnoreCase(user.getRole());
    }

    public boolean isUser(User user) {
        return user != null && "user".equalsIgnoreCase(user.getRole());
    }
}
