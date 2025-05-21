package com.example.wahana.model.service;

import com.example.wahana.model.entity.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    private Long idCounter = 3L; // Starting after dummy data

    public UserService() {
        // Data dummy untuk testing
        users.add(new User(1L, "Admin", "admin", "admin123", "admin", "admin@gmail.com"));
        users.add(new User(2L, "User Biasa", "user", "user123", "user", "user@gmail.com"));
    }

    public User login(String username, String password) {
        return users.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .filter(u -> u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public boolean register(User newUser) {
        if (newUser == null || newUser.getUsername() == null || newUser.getPassword() == null) {
            return false;
        }

        if (users.stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(newUser.getUsername()))) {
            return false;
        }

        newUser.setId(idCounter++);
        if (newUser.getRole() == null) {
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
        return new ArrayList<>(users);
    }
}