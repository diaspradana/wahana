package com.example.wahana.model.entity;

import com.example.wahana.WahanaApplication;

public class Wahana {
    private Long id;
    private String namaWahana;
    private String deskripsi;
    private String kategori;
    private double harga;
    private String gambar;
    private int stokTiket;

    public Wahana() {
    }

    public Wahana(Long id,String namaWahana, String deskripsi, String kategori ,double harga, String gambar, int stokTiket){
        this.id = id;
        this.namaWahana = namaWahana;
        this.deskripsi = deskripsi;
        this.kategori = kategori;
        this.harga = harga;
        this.gambar = gambar;
        this.stokTiket = stokTiket;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNamaWahana(){
        return namaWahana;
    }

    public void setNamaWahana(String namaWahana){
        this.namaWahana = namaWahana;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKategori () {
        return kategori;
    }

    public void setKategori (String kategori) {
        this.kategori = kategori;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getGambar () {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public int getStokTiket() {
        return stokTiket;
    }

    public void setstokTiket(int stokTiket) {
        this.stokTiket = stokTiket;
    }
}
