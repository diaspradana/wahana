package com.example.wahana.model.service;

import com.example.wahana.model.entity.Pemesanan;
import com.example.wahana.model.entity.User;
import com.example.wahana.model.entity.Wahana;
import com.example.wahana.repository.PemesananRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PemesananService {

    @Autowired
    private PemesananRepository pemesananRepository;

    // Menyimpan data pemesanan baru
    public Pemesanan buatPemesanan(User user, Wahana wahana, int jumlahTiket) {
        double totalHarga = wahana.getHarga() * jumlahTiket;
        Pemesanan pemesanan = new Pemesanan(user, wahana, jumlahTiket, totalHarga, LocalDateTime.now());
        return pemesananRepository.save(pemesanan);
    }

    // Mendapatkan semua pemesanan
    public List<Pemesanan> getSemuaPemesanan() {
        return pemesananRepository.findAll();
    }

    // Mendapatkan pemesanan berdasarkan user
    public List<Pemesanan> getPemesananByUser(User user) {
        return pemesananRepository.findByUser(user);
    }

    // Mendapatkan pemesanan berdasarkan ID
    public Pemesanan getPemesananById(Long id) {
        return pemesananRepository.findById(id).orElse(null);
    }

    // Hapus pemesanan
    public void hapusPemesanan(Long id) {
        pemesananRepository.deleteById(id);
    }
}