package com.example.wahana.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pemesanan")
public class Pemesanan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "wahana_id", nullable = false)
    private Wahana wahana;

    @Column(name = "jumlah_tiket", nullable = false)
    private int jumlahTiket;

    @Column(name = "total_harga", nullable = false)
    private double totalHarga;

    @Column(name = "tanggal_pemesanan", nullable = false)
    private LocalDateTime tanggalPemesanan;

    // === Constructor ===
    public Pemesanan() {
    }

    public Pemesanan(User user, Wahana wahana, int jumlahTiket, double totalHarga, LocalDateTime tanggalPemesanan) {
        this.user = user;
        this.wahana = wahana;
        this.jumlahTiket = jumlahTiket;
        this.totalHarga = totalHarga;
        this.tanggalPemesanan = tanggalPemesanan;
    }

    // === Getter & Setter ===
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