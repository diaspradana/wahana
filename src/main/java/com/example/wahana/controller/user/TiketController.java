package com.example.wahana.controller.user;

import com.example.wahana.model.entity.Wahana;
import com.example.wahana.model.service.WahanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user/tiket")
public class TiketController {

    private final WahanaService wahanaService;

    @Autowired
    public TiketController(WahanaService wahanaService) {
        this.wahanaService = wahanaService;
    }

    // Menampilkan daftar wahana dengan fitur pencarian dan sorting
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

        return "user/tiket";
    }

    // Menampilkan detail wahana dengan validasi stok
    @GetMapping("/detail/{id}")
    public String viewTiketDetail(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes) {

        Wahana wahana = wahanaService.getWahanaById(id);
        
        if (wahana == null) {
            redirectAttributes.addFlashAttribute("error", "Wahana tidak ditemukan");
            return "redirect:/user/tiket";
        }

        // Validasi stok
        if (wahana.getStokTiket() <= 0) {
            model.addAttribute("error", "Tiket untuk wahana ini sudah habis");
        }

        model.addAttribute("wahana", wahana);
        return "user/tiket_detail";
    }
    

}