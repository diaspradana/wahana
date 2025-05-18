package com.example.wahana.model.service;

import com.example.wahana.model.entity.HistoryPenjualan;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HistoryPenjualanService {

    private List<HistoryPenjualan> historyList = new ArrayList<>();

    public void catatPenjualan(String namaWahana, int jumlahTiket, double totalHarga, String metodePembayaran, String namaAdmin) {
        HistoryPenjualan history = new HistoryPenjualan(namaWahana, jumlahTiket, totalHarga, LocalDateTime.now(),
                metodePembayaran, namaAdmin);
        historyList.add(history);
    }

    public List<HistoryPenjualan> getAllHistoryPenjualan() {
        return historyList;
    }

    // Rekap berdasarkan bulan
    public Map<String, List<HistoryPenjualan>> getRekapBulanan() {
        return historyList.stream()
                .collect(Collectors.groupingBy(HistoryPenjualan::getBulanTahun));
    }

    // Total Pendapatan per Bulan
    public Map<String, Double> getTotalPendapatanPerBulan() {
        return historyList.stream()
                .collect(Collectors.groupingBy(
                        HistoryPenjualan::getBulanTahun,
                        Collectors.summingDouble(HistoryPenjualan::getTotalHarga)
                ));
    }

    // Total Tiket Terjual per Bulan
    public Map<String, Integer> getTotalTiketPerBulan() {
        return historyList.stream()
                .collect(Collectors.groupingBy(
                        HistoryPenjualan::getBulanTahun,
                        Collectors.summingInt(HistoryPenjualan::getJumlahTiket)
                ));
    }
}
