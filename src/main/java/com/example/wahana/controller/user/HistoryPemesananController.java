package com.example.wahana.controller.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoryPemesananController {

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
        private final String namaWahana;
        private final long harga;
        private final int qty;

        public DetailTiket(String namaWahana, long harga, int qty) {
            this.namaWahana = namaWahana;
            this.harga = harga;
            this.qty = qty;
        }

        public String getNamaWahana() { return namaWahana; }
        public long getHarga() { return harga; }
        public int getQty() { return qty; }
        public long getTotal() { return harga * qty; }
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
            this.tiket = new ArrayList<>();
        }

        public void addTiket(String ride, long harga, int qty) {
            tiket.add(new DetailTiket(ride, harga, qty));
        }

        public LocalDateTime getTanggal() { return tanggal; }
        public String getPembeli() { return pembeli; }
        public String getMetodePembayaran() { return metodePembayaran; }
        public List<DetailTiket> getTiket() { return tiket; }

        public int getTotalQty() {
            return tiket.stream().mapToInt(DetailTiket::getQty).sum();
        }

        public long getSubtotal() {
            return tiket.stream().mapToLong(DetailTiket::getTotal).sum();
        }
    }

    private List<Transaksi> demoData() {
        Transaksi t1 = new Transaksi(LocalDateTime.of(2025,5,15,14,35), "Alya Putri", "Kartu Kredit");
        t1.addTiket("Roller Coaster", 55000, 2);
        t1.addTiket("Ferris Wheel", 50000, 1);

        Transaksi t2 = new Transaksi(LocalDateTime.of(2025,4,2,10,12), "Alya Putri", "OVO");
        t2.addTiket("Haunted House", 65000, 3);

        return List.of(t1, t2);
    }
}
