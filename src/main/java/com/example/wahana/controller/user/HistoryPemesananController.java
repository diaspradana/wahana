package com.example.wahana.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.wahana.model.entity.Pemesanan;
import com.example.wahana.model.entity.Wahana;
import com.example.wahana.model.service.PemesananService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user/history") // Diubah ke path yang lebih standar
public class HistoryPemesananController {

    @Autowired
    private PemesananService pemesananService;

    @GetMapping
    public String tampilkanRiwayat(Model model) {
        try {
            List<Pemesanan> pemesananList = pemesananService.getPemesananByUserId(1L);
            
            if (pemesananList.isEmpty()) {
                // Data dummy dengan wahana
                Wahana wahana1 = new Wahana(1L, "Bianglala", "Deskripsi", "Kategori", 25000, "🎡", 100);
                Wahana wahana2 = new Wahana(2L, "Roller Coaster", "Deskripsi", "Kategori", 30000, "🎢", 100);

                pemesananList.add(new Pemesanan(1L, null, wahana1, 30, 750000.0, LocalDateTime.now()));
                pemesananList.add(new Pemesanan(2L, null, wahana2, 5, 150000.0, LocalDateTime.now()));
            }

            model.addAttribute("riwayat", pemesananList);
            return "user/history_pemesanan"; // Pastikan nama file template sesuai
        } catch (Exception e) {
            model.addAttribute("error", "Gagal memuat riwayat pemesanan");
            return "error"; // Buat template error.html sederhana
        }
    }
}