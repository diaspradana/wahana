package com.example.wahana.model.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Pemesanan {
    private Long id;
    private String userId; // ID atau username pengguna
    private Wahana wahana;
    private int jumlah;
    private double totalHarga;
    private LocalDateTime tanggalPemesanan;
    private String status; // "DRAFT", "COMPLETED", "CANCELLED"
    private String metodePembayaran;
    private String nomorTiket;

    // Format tanggal untuk display
    public String getTanggalPemesananFormatted() {
        if (tanggalPemesanan == null) return "";
        return tanggalPemesanan.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    // Hitung total harga
        public Long getId() {
            return id;
        }
    
        public void setId(Long id) {
            this.id = id;
        }
    
        public String getUserId() {
            return userId;
        }
    
        public void setUserId(String userId) {
            this.userId = userId;
        }
    
        public Wahana getWahana() {
            return wahana;
        }
    
        public void setWahana(Wahana wahana) {
            this.wahana = wahana;
        }
    
        public int getJumlah() {
            return jumlah;
        }
    
        public void setJumlah(int jumlah) {
            this.jumlah = jumlah;
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
    
        public String getStatus() {
            return status;
        }
    
        public void setStatus(String status) {
            this.status = status;
        }
    
        public String getMetodePembayaran() {
            return metodePembayaran;
        }
    
        public void setMetodePembayaran(String metodePembayaran) {
            this.metodePembayaran = metodePembayaran;
        }
    
        public String getNomorTiket() {
            return nomorTiket;
        }
    
        public void setNomorTiket(String nomorTiket) {
            this.nomorTiket = nomorTiket;
        }
    public void hitungTotalHarga() {
        // Pastikan wahana tidak null dan harga sudah diatur
         if (wahana != null && wahana.getHarga() > 0) {
            this.totalHarga = wahana.getHarga() * jumlah;
        }
       
    }

    // Generate nomor tiket unik
    public void generateNomorTiket() {
    // Tambahkan null check
    if (wahana == null || tanggalPemesanan == null || wahana.getNamaWahana() == null) {
        this.nomorTiket = "TKT-000000";
        return;
    }
    
    try {
        // Ambil 3 huruf pertama nama wahana (pastikan nama tidak kosong)
        String namaWahana = wahana.getNamaWahana().trim();
        String prefix = namaWahana.substring(0, Math.min(3, namaWahana.length())).toUpperCase();
        
        // Generate timestamp
        String timestamp = String.valueOf(tanggalPemesanan.toEpochSecond(java.time.ZoneOffset.UTC));
        
        // Gabungkan prefix dan timestamp
        this.nomorTiket = prefix + "-" + timestamp.substring(Math.max(0, timestamp.length() - 6));
    } catch (Exception e) {
        this.nomorTiket = "TKT-ERROR";
    }
}
}