package com.example.wahana.controller;

import com.example.wahana.model.entity.User;
import com.example.wahana.model.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // GET Login Page
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("title", "Login");
        return "auth/login";
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

    // GET Register Page (NEW - This was missing)
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    // POST Register Action
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, 
                             Model model,
                             HttpSession session) {
        try {
            user.setRole("user"); // Set default role
            boolean isRegistered = userService.register(user);
            
            if (isRegistered) {
                return "redirect:/login";
            } else {
                model.addAttribute("error", "Username sudah digunakan");
                return "auth/register";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Gagal mendaftar: " + e.getMessage());
            return "auth/register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }

    @GetMapping("/user/dashboard")
    public String userDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !"user".equalsIgnoreCase(user.getRole())) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "user/user_dashboard";
    }
}