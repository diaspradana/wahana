package com.example.wahana.model.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryPenjualan {

    private Pemesanan pemesanan;
    private int jumlahTiket;
    private double totalHarga;
    private LocalDateTime tanggalTransaksi;
    private String metodePembayaran;
    private String namaAdmin;

    public HistoryPenjualan() {}

    public HistoryPenjualan(Pemesanan pemesanan, int jumlahTiket, double totalHarga, LocalDateTime tanggalTransaksi, String metodePembayaran, String namaAdmin) {
        this.pemesanan = pemesanan;
        this.jumlahTiket = jumlahTiket;
        this.totalHarga = totalHarga;
        this.tanggalTransaksi = tanggalTransaksi;
        this.metodePembayaran = metodePembayaran;
        this.namaAdmin = namaAdmin;
    }

    public Pemesanan getPemesanan() {
        return pemesanan;
    }

    public void setPemesanan(Pemesanan pemesanan) {
        this.pemesanan = pemesanan;
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

    public LocalDateTime getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(LocalDateTime tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public String getNamaAdmin() {
        return namaAdmin;
    }

    public void setNamaAdmin(String namaAdmin) {
        this.namaAdmin = namaAdmin;
    }

    public String getBulanTahun() {
        return tanggalTransaksi.format(DateTimeFormatter.ofPattern("MM-yyyy"));
    }
}

