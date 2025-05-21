package com.example.wahana.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.wahana.model.service.AdminService;

@Controller
public class HistoryPenjualanController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/history")
    public String showHistoryPenjualan(Model model) {
        model.addAttribute("riwayatPenjualan", adminService.getAllPemesanan());
        return "admin/history_penjualan";
    }
}