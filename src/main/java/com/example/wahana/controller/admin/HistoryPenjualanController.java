package com.example.wahana.controller.admin;

import com.example.wahana.model.entity.HistoryPenjualan;
import com.example.wahana.model.service.HistoryPenjualanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/history")
public class HistoryPenjualanController {

    @Autowired
    private HistoryPenjualanService historyPenjualanService;

    // Get semua riwayat
    @GetMapping
    public List<HistoryPenjualan> getHistory() {
        return historyPenjualanService.getAllHistoryPenjualan();
    }

    // Get rekap bulanan
    @GetMapping("/rekap/bulanan")
    public Map<String, List<HistoryPenjualan>> getRekapBulanan() {
        return historyPenjualanService.getRekapBulanan();
    }

    // Get total pendapatan per bulan
    @GetMapping("/pendapatan/bulanan")
    public Map<String, Double> getTotalPendapatanPerBulan() {
        return historyPenjualanService.getTotalPendapatanPerBulan();
    }

    // Get total tiket per bulan
    @GetMapping("/tiket/bulanan")
    public Map<String, Integer> getTotalTiketPerBulan() {
        return historyPenjualanService.getTotalTiketPerBulan();
    }
}
