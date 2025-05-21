package com.example.wahana.model.service;

import org.springframework.stereotype.Service;

import com.example.wahana.model.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public UserService() {
        // Data dummy untuk testing
        users.add(new User(1L, "Admin", "admin", "admin123", "admin"));
        users.add(new User(2L, "User Biasa", "user", "user123", "user"));
    }

    public User login(String username, String password) {
        if (username == null || password == null) {
            return null;
        }

        return users.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username)) 
                .filter(u -> u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public boolean register(User newUser) {
        if (newUser == null || 
            newUser.getUsername() == null || 
            newUser.getPassword() == null ||
            newUser.getRole() == null) {
            return false;
        }

        // Cek apakah username sudah ada
        boolean usernameExists = users.stream()
                .anyMatch(u -> u.getUsername().equalsIgnoreCase(newUser.getUsername()));

        if (usernameExists) {
            return false;
        }

        // Set role default jika tidak diisi
        if (newUser.getRole().isBlank()) {
            newUser.setRole("user");
        }

        users.add(newUser);
        return true;
    }

    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users); // Return copy untuk menghindari modifikasi langsung
    }
}