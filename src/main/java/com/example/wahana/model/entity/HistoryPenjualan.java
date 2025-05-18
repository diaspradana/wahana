package com.example.wahana.model.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryPenjualan {
    private String namaWahana;
    private int jumlahTiket;
    private double totalHarga;
    private LocalDateTime tanggalWaktu;
    private String metodePembayaran;
    private String namaAdmin;
    private String bulanTahun;

    public HistoryPenjualan() {
    }

    public HistoryPenjualan(String namaWahana, int jumlahTiket, double totalHarga, LocalDateTime tanggalWaktu,
                            String metodePembayaran, String namaAdmin) {
        this.namaWahana = namaWahana;
        this.jumlahTiket = jumlahTiket;
        this.totalHarga = totalHarga;
        this.tanggalWaktu = tanggalWaktu;
        this.metodePembayaran = metodePembayaran;
        this.namaAdmin = namaAdmin;
        this.bulanTahun = tanggalWaktu.format(DateTimeFormatter.ofPattern("MM-yyyy"));
    }

    // Getter & Setter
    public String getNamaWahana() {
        return namaWahana;
    }

    public void setNamaWahana(String namaWahana) {
        this.namaWahana = namaWahana;
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

    public LocalDateTime getTanggalWaktu() {
        return tanggalWaktu;
    }

    public void setTanggalWaktu(LocalDateTime tanggalWaktu) {
        this.tanggalWaktu = tanggalWaktu;
        this.bulanTahun = tanggalWaktu.format(DateTimeFormatter.ofPattern("MM-yyyy"));
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
        return bulanTahun;
    }

    public void setBulanTahun(String bulanTahun) {
        this.bulanTahun = bulanTahun;
    }
}
