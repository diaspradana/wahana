package com.example.wahana.model.service;

import com.example.wahana.model.entity.Pemesanan;
import com.example.wahana.model.entity.Wahana;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    private final PemesananService pemesananService;
    private final List<Wahana> daftarWahana = new ArrayList<>();

    public AdminService(PemesananService pemesananService) {
        this.pemesananService = pemesananService;
    }

    public List<Wahana> getAllWahana() {
        return daftarWahana;
    }

    // Method untuk mendapatkan semua pemesanan
    public List<Pemesanan> getAllPemesanan() {
        return pemesananService.getAllPemesanan();
    }

    public void simpanWahana(Wahana wahana) {
        wahana.setId((long) (daftarWahana.size() + 1));
        daftarWahana.add(wahana);
    }

    public void hapusWahana(Long id) {
        daftarWahana.removeIf(w -> w.getId().equals(id));
    }

    public int getTotalWahana() {
        return daftarWahana.size();
    }

    public int getTotalPemesanan() {
        return pemesananService.getAllPemesanan().size();
    }

    public double getTotalPendapatan() {
        return pemesananService.getAllPemesanan().stream()
                .mapToDouble(Pemesanan::getTotalHarga)
                .sum();
    }
}
