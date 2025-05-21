package com.example.wahana.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.wahana.model.service.AdminService;

@Controller
public class DashboardAdminController {

    private final AdminService adminService;

    public DashboardAdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("totalWahana", adminService.getTotalWahana());
        model.addAttribute("totalPemesanan", adminService.getTotalPemesanan());
        model.addAttribute("totalPendapatan", adminService.getTotalPendapatan());
        return "admin/admin_dashboard";
    }
}