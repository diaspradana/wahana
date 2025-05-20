package com.example.wahana.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.wahana.model.entity.User;
import com.example.wahana.model.entity.Pemesanan;
import com.example.wahana.model.entity.Wahana;
import com.example.wahana.model.service.WahanaService;

@Controller
@RequestMapping("/user/keranjang")
public class KeranjangController {

    private List<Pemesanan> keranjang = new ArrayList<>();
    private Long idCounter = 1L;

    @Autowired
    private WahanaService wahanaService;

    @PostMapping("/tambah")
    public String tambahKeKeranjang(@RequestParam Long wahanaId, @RequestParam int jumlahTiket) {
        Wahana wahana = wahanaService.getWahanaByID(wahanaId);
        if (wahana != null) {
                double total = wahana.getHarga() * jumlahTiket;
                Pemesanan pemesanan = new Pemesanan(
                idCounter++, 
                new User(1L, "User Biasa", "user1", "pass", "user"), 
                wahana, 
                jumlahTiket, 
                total, 
                LocalDateTime.now()
            );
            keranjang.add(pemesanan);
        }
        return "redirect:/user/tiket";
    }

    @GetMapping
    public String lihatKeranjang(Model model) {
        model.addAttribute("keranjang", keranjang);
        return "user/keranjang";
    }

    @GetMapping("/delete/{id}")
    public String hapusItem(@PathVariable Long id) {
        keranjang.removeIf(p -> p.getId().equals(id));
        return "redirect:/user/keranjang";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        double total = keranjang.stream().mapToDouble(Pemesanan::getTotalHarga).sum();
        model.addAttribute("keranjang", keranjang);
        model.addAttribute("totalPembayaran", total);
        return "user/checkout";
    }

    @PostMapping("/bayar")
    public String prosesBayar(RedirectAttributes redirectAttributes) {
        keranjang.clear();
        redirectAttributes.addFlashAttribute("success", "Pembayaran berhasil!");
        return "redirect:/user/tiket";
    }
}
