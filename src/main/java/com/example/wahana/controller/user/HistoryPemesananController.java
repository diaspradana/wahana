package com.example.wahana.controller.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.wahana.model.entity.Wahana;
import com.example.wahana.model.service.WahanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoryPemesananController {

    @Autowired
    private WahanaService wahanaService;

    @GetMapping("/history")
    public String getHistory(Model model) {
        List<Transaksi> transaksiList = demoData();

        int totalTiket = transaksiList.stream()
                .mapToInt(Transaksi::getTotalQty)
                .sum();

        long totalBiaya = transaksiList.stream()
                .mapToLong(Transaksi::getSubtotal)
                .sum();

        Map<String, Integer> tiketPerBulan = transaksiList.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getTanggal().format(DateTimeFormatter.ofPattern("MMMM yyyy")),
                        Collectors.summingInt(Transaksi::getTotalQty)
                ));

        String bulanAktif = tiketPerBulan.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("-");

        model.addAttribute("transaksi", transaksiList);
        model.addAttribute("totalTiket", totalTiket);
        model.addAttribute("totalBiaya", totalBiaya);
        model.addAttribute("bulanAktif", bulanAktif);

        return "history";
    }

    public static class DetailTiket {
        private final Wahana wahana;
        private final int qty;

        public DetailTiket(Wahana wahana, int qty) {
            this.wahana = wahana;
            this.qty = qty;
        }

        public Wahana getWahana() {
            return wahana;
        }
        public int getQty() {
            return qty;
        }
        public long getTotal() {
            return (long) (wahana.getHarga() * qty);
        }
    }

    public static class Transaksi {
        private final LocalDateTime tanggal;
        private final String pembeli;
        private final String metodePembayaran;
        private List<DetailTiket> tiket = new ArrayList<>();

        public Transaksi(LocalDateTime tanggal, String pembeli, String metodePembayaran) {
            this.tanggal = tanggal;
            this.pembeli = pembeli;
            this.metodePembayaran = metodePembayaran;
            //this.tiket = new ArrayList<>();
        }

        public void addTiket(Wahana wahana, int qty) {
            tiket.add(new DetailTiket(wahana, qty));
        }

        public LocalDateTime getTanggal() {
            return tanggal;
        }
        public String getPembeli() {
            return pembeli;
        }
        public String getMetodePembayaran() {
            return metodePembayaran;
        }
        public List<DetailTiket> getTiket() {
            return tiket;
        }

        public int getTotalQty() {
            return tiket.stream().mapToInt(DetailTiket::getQty).sum();
        }

        public long getSubtotal() {
            return tiket.stream().mapToLong(DetailTiket::getTotal).sum();
        }
    }

    private List<Transaksi> demoData() {
        // Ambil wahana dari service
        Wahana bianglala = wahanaService.getWahanaById(1L);
        Wahana rumahHantu = wahanaService.getWahanaById(2L);
        Wahana rollerCoaster = wahanaService.getWahanaById(3L);

        Transaksi t1 = new Transaksi(LocalDateTime.of(2025, 5, 15, 14, 35), "Alya Putri", "Kartu Kredit");
        if (bianglala != null) t1.addTiket(bianglala, 2);
        if (rumahHantu != null) t1.addTiket(rumahHantu, 1);

        Transaksi t2 = new Transaksi(LocalDateTime.of(2025, 4, 2, 10, 12), "Alya Putri", "OVO");
        if (rollerCoaster != null) t2.addTiket(rollerCoaster, 3);

        return List.of(t1, t2);
    }
}
