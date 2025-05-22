package com.example.wahana.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.wahana.model.service.AdminService;

// Controller untuk halaman riwayat penjualan admin
@Controller
@RequestMapping("/admin/history")
public class HistoryPenjualanController {

    private final AdminService adminService;

    // Konstruktor untuk inisialisasi service
    @Autowired
    public HistoryPenjualanController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Menampilkan halaman riwayat penjualan
    @GetMapping
    public String showHistoryPenjualan(Model model) {
        // Tambahkan daftar pemesanan ke model
        model.addAttribute("pemesananList", adminService.getAllPemesanan());

        // Tambahkan total pendapatan ke model
        model.addAttribute("totalPendapatan", adminService.getTotalPendapatan());

        // Kembalikan nama file HTML view
        return "admin/history_penjualan";
    }
}
