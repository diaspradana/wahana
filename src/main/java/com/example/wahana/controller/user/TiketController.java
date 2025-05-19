package com.example.wahana.controller.user;

import com.example.wahana.model.entity.Pemesanan;
import com.example.wahana.model.service.PemesananService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/tiket")
public class TiketController {

    @Autowired
    private PemesananService pemesananService;

    // Mendapatkan daftar pemesanan yang telah diselesaikan oleh pengguna
    @GetMapping("/riwayat/{userId}")
    public List<Pemesanan> getRiwayatPemesanan(@PathVariable String userId) {
        return pemesananService.getOrderHistory(userId);
    }

    // Mendapatkan detail pemesanan berdasarkan ID
    @GetMapping("/detail/{pemesananId}")
    public Pemesanan getDetailPemesanan(@PathVariable Long pemesananId) {
        return pemesananService.getOrderDetail(pemesananId);
    }

    // Membatalkan pemesanan berdasarkan ID
    @PostMapping("/batal/{pemesananId}")
    public void batalPemesanan(@PathVariable Long pemesananId) {
        pemesananService.cancelOrder(pemesananId);
    }
}
