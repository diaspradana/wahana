package com.example.wahana.controller.admin;

import com.example.wahana.model.service.DashboardService;
import com.example.wahana.model.service.HistoryPenjualanService;
import com.example.wahana.model.service.WahanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/dashboard")
public class DashboardAdminController {

    private final DashboardService dashboardService;
    private final WahanaService wahanaService;
    private final HistoryPenjualanService historyService;

    @Autowired
    public DashboardAdminController(DashboardService dashboardService, WahanaService wahanaService, HistoryPenjualanService historyService) {
        this.dashboardService = dashboardService;
        this.wahanaService = wahanaService;
        this.historyService = historyService;
    }

    @GetMapping
    public String showDashboard(Model model) {
        // Data statistik
        // model.addAttribute("totalWahana", wahanaService.countAllWahana());
        // model.addAttribute("wahanaAktif", wahanaService.countActiveWahana());
        // model.addAttribute("totalPendapatan", historyService.getTotalRevenue());
        // model.addAttribute("transaksiTerakhir", historyService.getRecentTransactions(5));
        
        // Data untuk chart
        model.addAttribute("pendapatanBulanan", dashboardService.getMonthlyRevenue());
        model.addAttribute("kategoriTerpopuler", dashboardService.getPopularCategories());
        
        return "admin/dashboard";
    }
}