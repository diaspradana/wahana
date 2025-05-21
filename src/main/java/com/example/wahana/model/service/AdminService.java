package com.example.wahana.model.service;

import org.springframework.stereotype.Service;

import com.example.wahana.model.entity.Pemesanan;
import com.example.wahana.model.entity.Wahana;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    private List<Wahana> daftarWahana = new ArrayList<>();
    private List<Pemesanan> daftarPemesanan = new ArrayList<>();

    public List<Wahana> getAllWahana() {
        return daftarWahana;
    }

    public void simpanWahana(Wahana wahana) {
        wahana.setId((long) (daftarWahana.size() + 1));
        daftarWahana.add(wahana);
    }

    public void hapusWahana(Long id) {
        daftarWahana.removeIf(w -> w.getId().equals(id));
    }

    public List<Pemesanan> getAllPemesanan() {
        return daftarPemesanan;
    }

    public int getTotalWahana() {
        return daftarWahana.size();
    }

    public int getTotalPemesanan() {
        return daftarPemesanan.size();
    }

    public double getTotalPendapatan() {
        return daftarPemesanan.stream()
            .mapToDouble(Pemesanan::getTotalHarga)
            .sum();
    }

    // Dummy method to simulate adding pemesanan (if needed externally)
    public void tambahPemesanan(Pemesanan pemesanan) {
        daftarPemesanan.add(pemesanan);
    }
}