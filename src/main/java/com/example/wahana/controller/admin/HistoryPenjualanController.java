package com.example.wahana.controller.admin;

import com.example.wahana.model.entity.HistoryPenjualan;
import com.example.wahana.model.service.HistoryPenjualanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/history")
public class HistoryPenjualanController {

    private final HistoryPenjualanService historyPenjualanService;

    public HistoryPenjualanController(HistoryPenjualanService historyPenjualanService) {
        this.historyPenjualanService = historyPenjualanService;
    }

    @GetMapping("/rekap/bulanan")
    public Map<String, List<HistoryPenjualan>> getRekapBulanan() {
        return historyPenjualanService.getRekapBulanan();
    }

    @GetMapping("/pendapatan/bulanan")
    public Map<String, Double> getTotalPendapatanPerBulan() {
        return historyPenjualanService.getTotalPendapatanPerBulan();
    }

    @GetMapping("/tiket/bulanan")
    public Map<String, Integer> getTotalTiketPerBulan() {
        return historyPenjualanService.getTotalTiketPerBulan();
    }

    @GetMapping
    public List<HistoryPenjualan> getAll() {
        return historyPenjualanService.getAllHistoryPenjualan();
    }
}
