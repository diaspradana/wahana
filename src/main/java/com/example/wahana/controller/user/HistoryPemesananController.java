package com.example.wahana.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.wahana.model.entity.Pemesanan;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HistoryPemesananController {

    // Simulasi database: gunakan list statis (nanti bisa diganti pakai service/database)
    private List<Pemesanan> riwayatPemesanan = new ArrayList<>();

    public HistoryPemesananController() {
        // Dummy data (bisa dihapus nanti)
        riwayatPemesanan.add(new Pemesanan(1L, null, null, 2, 50000.0, java.time.LocalDateTime.now().minusDays(2)));
        riwayatPemesanan.add(new Pemesanan(2L, null, null, 3, 75000.0, java.time.LocalDateTime.now().minusDays(1)));
    }

    @GetMapping("/user/history")
    public String tampilkanRiwayat(Model model) {
        model.addAttribute("riwayat", riwayatPemesanan);
        return "user/history_pemesanan";
    }
}
