package com.example.wahana.controller;

import com.example.wahana.model.entity.User;
import com.example.wahana.model.service.UserService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // GET Login Page
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("title", "Login"); // Tambahkan title untuk halaman
        return "auth/login"; // Gunakan lowercase untuk konsistensi
    }

    // POST Login Action
    @PostMapping("/login")
    public String login(@RequestParam String username,
                       @RequestParam String password,
                       HttpSession session,
                       Model model) {
        try {
            User user = userService.login(username, password);
            if (user != null) {
                session.setAttribute("loggedInUser", user);
                
                // Redirect berdasarkan role
                return switch (user.getRole().toLowerCase()) {
                    case "admin" -> "redirect:/admin/dashboard";
                    case "user" -> "redirect:/user/dashboard";
                    default -> "redirect:/login";
                };
            } else {
                model.addAttribute("error", "Username atau password salah");
                return "auth/login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Terjadi kesalahan saat login");
            return "auth/login";
        }
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }

    // User Dashboard
    @GetMapping("/user/dashboard")
    public String userDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !"user".equalsIgnoreCase(user.getRole())) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "user/user_dashboard";
    }

    // Tambahkan method untuk registrasi
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("title", "Registration");
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        try {
            userService.register(user);
            return "redirect:/login?register";
        } catch (Exception e) {
            model.addAttribute("error", "Gagal mendaftar: " + e.getMessage());
            return "auth/register";
        }
    }
}