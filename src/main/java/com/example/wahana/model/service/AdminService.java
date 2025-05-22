package com.example.wahana.model.service;

import com.example.wahana.model.entity.Pemesanan;
import com.example.wahana.model.service.PemesananService;
import com.example.wahana.model.service.WahanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final PemesananService pemesananService;
    private final WahanaService wahanaService;

    @Autowired
    public AdminService(PemesananService pemesananService, WahanaService wahanaService) {
        this.pemesananService = pemesananService;
        this.wahanaService = wahanaService;
    }

    public List<Pemesanan> getAllPemesanan() {
        return pemesananService.getAllPemesanan();
    }

    public int getTotalWahana() {
        return wahanaService.getAllWahana().size();
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
