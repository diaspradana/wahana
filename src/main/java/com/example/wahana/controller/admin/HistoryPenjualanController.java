package com.example.wahana.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.wahana.model.service.AdminService;

@Controller
@RequestMapping("/admin/history")
public class HistoryPenjualanController {

    private final AdminService adminService;

    @Autowired
    public HistoryPenjualanController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String showHistoryPenjualan(Model model) {
        model.addAttribute("pemesananList", adminService.getAllPemesanan());
        return "admin/history_penjualan";
    }
}