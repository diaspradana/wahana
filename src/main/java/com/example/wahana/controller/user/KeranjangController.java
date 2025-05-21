package com.example.wahana.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.wahana.model.entity.User;
import com.example.wahana.model.entity.Pemesanan;
import com.example.wahana.model.entity.Wahana;
import com.example.wahana.model.service.WahanaService;
import com.example.wahana.model.service.PemesananService;

@Controller
@RequestMapping("/user/keranjang")
public class KeranjangController {

    private List<Pemesanan> keranjang = new ArrayList<>();
    private Long idCounter = 1L;

    @Autowired
    private WahanaService wahanaService;
    
    @Autowired
    private PemesananService pemesananService;

    @PostMapping("/tambah")
    public String tambahKeKeranjang(@RequestParam Long wahanaId, 
                                  @RequestParam int jumlahTiket,
                                  RedirectAttributes redirectAttributes) {
        Wahana wahana = wahanaService.getWahanaById(wahanaId);
        if (wahana != null) {
            // Validasi stok sebelum menambah ke keranjang
            if (wahana.getStokTiket() < jumlahTiket) {
                redirectAttributes.addFlashAttribute("error", 
                    "Stok tidak mencukupi untuk " + wahana.getNamaWahana());
                return "redirect:/user/tiket/detail/" + wahanaId;
            }
            
            double total = wahana.getHarga() * jumlahTiket;
            Pemesanan pemesanan = new Pemesanan(
                idCounter++, 
                new User(1L, "User Biasa", "user1", "pass", "user", "user"), 
                wahana, 
                jumlahTiket, 
                total, 
                LocalDateTime.now()
            );
            keranjang.add(pemesanan);
            redirectAttributes.addFlashAttribute("success", 
                "Wahana " + wahana.getNamaWahana() + " ditambahkan ke keranjang");
        }
        return "redirect:/user/tiket";
    }

    @GetMapping
    public String lihatKeranjang(Model model) {
        // Hitung total harga semua item di keranjang
        double totalHarga = keranjang.stream()
            .mapToDouble(Pemesanan::getTotalHarga)
            .sum();
            
        model.addAttribute("keranjang", keranjang);
        model.addAttribute("totalHarga", totalHarga);
        return "user/keranjang";
    }

    @GetMapping("/delete/{id}")
    public String hapusItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        keranjang.removeIf(p -> p.getId().equals(id));
        redirectAttributes.addFlashAttribute("success", "Item berhasil dihapus");
        return "redirect:/user/keranjang";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        double total = keranjang.stream()
            .mapToDouble(Pemesanan::getTotalHarga)
            .sum();
            
        model.addAttribute("keranjang", keranjang);
        model.addAttribute("totalPembayaran", total);
        return "user/checkout";
    }

    @PostMapping("/bayar")
    public String prosesBayar(RedirectAttributes redirectAttributes) {
        // Validasi stok sebelum checkout
        for (Pemesanan p : keranjang) {
            Wahana wahana = wahanaService.getWahanaById(p.getWahana().getId());
            if (wahana == null || wahana.getStokTiket() < p.getJumlahTiket()) {
                redirectAttributes.addFlashAttribute("error", "Stok wahana " + 
                    (wahana != null ? wahana.getNamaWahana() : "") + " tidak mencukupi");
                return "redirect:/user/keranjang";
            }
        }

        // Simpan semua pemesanan dan kurangi stok
        for (Pemesanan p : keranjang) {
            // Kurangi stok
            wahanaService.kurangiStok(p.getWahana().getId(), p.getJumlahTiket());
            
            // Simpan ke riwayat pemesanan
            pemesananService.savePemesanan(p);
        }

        keranjang.clear();
        redirectAttributes.addFlashAttribute("success", "Pembayaran berhasil!");
        return "redirect:/user/history";
    }
}