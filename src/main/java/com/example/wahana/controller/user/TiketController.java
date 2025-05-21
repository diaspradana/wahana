package com.example.wahana.controller.user;

import com.example.wahana.model.entity.Wahana;
import com.example.wahana.model.service.WahanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/tiket")
public class TiketController {

    @Autowired
    private WahanaService wahanaService;

    // Menampilkan daftar wahana
    @GetMapping
    public String viewTiket(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {

        model.addAttribute("wahanaList", wahanaService.cariWahana(keyword, sortBy, sortDir));
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "user/tiket"; // templates/user/tiket.html
    }

    // Menampilkan detail wahana
    @GetMapping("/detail/{id}")
    public String viewTiketDetail(@PathVariable Long id, Model model) {
        Wahana wahana = wahanaService.getWahanaById(id);
        if (wahana == null) {
            model.addAttribute("error", "Wahana tidak ditemukan");
            return "redirect:/user/tiket";
        }

        // Tambahkan validasi stok
        if (wahana.getStokTiket() <= 0) {
            model.addAttribute("error", "Tiket untuk wahana ini sudah habis");
        }

        model.addAttribute("wahana", wahana);
        return "user/tiket_detail";
    }
}
