package com.example.wahana.controller;

import com.example.wahana.model.User;
import com.example.wahana.service.UserService;
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
    public String showLoginForm() {
        return "auth/Login"; // sesuaikan dengan path file HTML
    }

    // POST Login Action
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("loggedInUser", user);
            if ("admin".equals(user.getRole())) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/user/dashboard";
            }
        }
        model.addAttribute("error", "Username atau password salah");
        return "auth/Login";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // Admin Dashboard
    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null && "admin".equals(user.getRole())) {
            return "admin/admin_dashboard"; // sesuaikan file HTML
        }
        return "redirect:/login";
    }

    // User Dashboard
    @GetMapping("/user/dashboard")
    public String userDashboard(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null && "user".equals(user.getRole())) {
            return "user/user_dashboard"; // sesuaikan file HTML
        }
        return "redirect:/login";
    }
}
