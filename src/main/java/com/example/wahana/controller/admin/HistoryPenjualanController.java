package com.example.wahana.controller.admin;

import com.example.wahana.model.entity.HistoryPenjualan;
import com.example.wahana.model.service.HistoryPenjualanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Controller untuk mengatur endpoint riwayat penjualan yang diakses oleh admin
@RestController
@RequestMapping("/admin/history")
public class HistoryPenjualanController {

    // Injeksi service yang menangani logika bisnis history penjualan
    @Autowired
    private HistoryPenjualanService historyPenjualanService;

    // Mendapatkan semua riwayat penjualan dari database
    @GetMapping
    public List<HistoryPenjualan> getHistory() {
        return historyPenjualanService.getAllHistoryPenjualan();
    }

    // Mendapatkan rekap penjualan yang dikelompokkan per bulan
    @GetMapping("/rekap/bulanan")
    public Map<String, List<HistoryPenjualan>> getRekapBulanan() {
        return historyPenjualanService.getRekapBulanan();
    }

    // Mendapatkan total pendapatan tiap bulan
    @GetMapping("/pendapatan/bulanan")
    public Map<String, Double> getTotalPendapatanPerBulan() {
        return historyPenjualanService.getTotalPendapatanPerBulan();
    }

    // Mendapatkan jumlah tiket yang terjual tiap bulan
    @GetMapping("/tiket/bulanan")
    public Map<String, Integer> getTotalTiketPerBulan() {
        return historyPenjualanService.getTotalTiketPerBulan();
    }
}
