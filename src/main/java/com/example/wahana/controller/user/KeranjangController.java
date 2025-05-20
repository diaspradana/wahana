package com.example.wahana.controller.user;

import com.example.wahana.model.entity.Pemesanan;
import com.example.wahana.model.entity.User;
import com.example.wahana.model.entity.Wahana;
import com.example.wahana.model.service.PemesananService;
import com.example.wahana.model.service.UserService;
import com.example.wahana.model.service.WahanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user/keranjang")
public class KeranjangController {

    @Autowired
    private PemesananService pemesananService;

    @Autowired
    private WahanaService wahanaService;

    @Autowired
    private UserService userService;

    // Tampilkan daftar keranjang (pemesanan user)
    @GetMapping
    public String tampilkanKeranjang(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Pemesanan> daftarPemesanan = pemesananService.getPemesananByUser(user);

        model.addAttribute("daftarPemesanan", daftarPemesanan);
        return "user/keranjang"; // sesuaikan dengan nama file HTML-nya
    }

    // Tambah pesanan ke keranjang
    @PostMapping("/tambah")
    public String tambahKeKeranjang(@RequestParam Long wahanaId,
                                     @RequestParam int jumlahTiket,
                                     Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Wahana wahana = wahanaService.getWahanaById(wahanaId);

        if (user != null && wahana != null) {
            pemesananService.buatPemesanan(user, wahana, jumlahTiket);
        }

        return "redirect:/user/keranjang";
    }

    // Hapus item dari keranjang
    @PostMapping("/hapus/{id}")
    public String hapusDariKeranjang(@PathVariable("id") Long id) {
        pemesananService.hapusPemesanan(id);
        return "redirect:/user/keranjang";
    }
}