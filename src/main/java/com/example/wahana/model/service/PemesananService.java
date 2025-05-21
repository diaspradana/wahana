package com.example.wahana.model.service;

import com.example.wahana.model.entity.Pemesanan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; // Import yang ditambahkan

@Service
public class PemesananService {
    private final List<Pemesanan> pemesananList = new ArrayList<>();
    private Long idCounter = 1L;

    public List<Pemesanan> getAllPemesanan() {
        return new ArrayList<>(pemesananList); // Return copy untuk keamanan
    }

    public Pemesanan savePemesanan(Pemesanan pemesanan) {
        if (pemesanan.getId() == null) {
            pemesanan.setId(idCounter++);
            pemesananList.add(pemesanan);
        } else {
            // Update existing pemesanan jika diperlukan
            pemesananList.removeIf(p -> p.getId().equals(pemesanan.getId()));
            pemesananList.add(pemesanan);
        }
        return pemesanan;
    }

    public List<Pemesanan> getPemesananByUserId(Long userId) {
        return pemesananList.stream()
                .filter(p -> p.getUser() != null && p.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }
}