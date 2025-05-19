package com.example.wahana.model.service;

import com.example.wahana.model.entity.HistoryPenjualan;
import com.example.wahana.model.entity.Pemesanan;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HistoryPenjualanService {

    private final List<HistoryPenjualan> historyList = new ArrayList<>();

    public void catatPenjualan(Pemesanan pemesanan, String namaAdmin) {
        HistoryPenjualan history = new HistoryPenjualan(
                pemesanan,
                pemesanan.getJumlah(),
                pemesanan.getTotalHarga(),
                LocalDateTime.now(),
                pemesanan.getMetodePembayaran(),
                namaAdmin
        );
        historyList.add(history);
    }

    public List<HistoryPenjualan> getAllHistoryPenjualan() {
        return historyList;
    }

    public Map<String, List<HistoryPenjualan>> getRekapBulanan() {
        return historyList.stream()
                .collect(Collectors.groupingBy(HistoryPenjualan::getBulanTahun));
    }

    public Map<String, Double> getTotalPendapatanPerBulan() {
        return historyList.stream()
                .collect(Collectors.groupingBy(
                        HistoryPenjualan::getBulanTahun,
                        Collectors.summingDouble(HistoryPenjualan::getTotalHarga)
                ));
    }

    public Map<String, Integer> getTotalTiketPerBulan() {
        return historyList.stream()
                .collect(Collectors.groupingBy(
                        HistoryPenjualan::getBulanTahun,
                        Collectors.summingInt(HistoryPenjualan::getJumlahTiket)
                ));
    }
}
