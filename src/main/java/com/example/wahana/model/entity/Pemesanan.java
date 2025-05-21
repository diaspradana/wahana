package com.example.wahana.model.entity;

import java.time.LocalDateTime;

public class Pemesanan {

    private Long id;
    private User user;
    private Wahana wahana;
    private int jumlahTiket;
    private double totalHarga;
    private LocalDateTime tanggalPemesanan;

    public Pemesanan() {}

    public Pemesanan(Long id, User user, Wahana wahana, int jumlahTiket, double totalHarga, LocalDateTime tanggalPemesanan) {
        this.id = id;
        this.user = user;
        this.wahana = wahana;
        this.jumlahTiket = jumlahTiket;
        this.totalHarga = totalHarga;
        this.tanggalPemesanan = tanggalPemesanan;
    }

    // Getter & Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Wahana getWahana() {
        return wahana;
    }

    public void setWahana(Wahana wahana) {
        this.wahana = wahana;
    }

    public int getJumlahTiket() {
        return jumlahTiket;
    }

    public void setJumlahTiket(int jumlahTiket) {
        this.jumlahTiket = jumlahTiket;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
    }

    public LocalDateTime getTanggalPemesanan() {
        return tanggalPemesanan;
    }

    public void setTanggalPemesanan(LocalDateTime tanggalPemesanan) {
        this.tanggalPemesanan = tanggalPemesanan;
    }
}
