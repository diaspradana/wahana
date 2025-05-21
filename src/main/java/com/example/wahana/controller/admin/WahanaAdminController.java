package com.example.wahana.controller.admin;

import com.example.wahana.model.entity.Wahana;
import com.example.wahana.model.service.WahanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/wahana")
public class WahanaAdminController {

    private final WahanaService wahanaService;

    @Autowired
    public WahanaAdminController(WahanaService wahanaService) {
        this.wahanaService = wahanaService;
    }

    @GetMapping
    public String listWahana(Model model) {
        model.addAttribute("wahanaList", wahanaService.getAllWahana());
        return "admin/wahana_admin";
    }

    @GetMapping("/tambah")
    public String showAddForm(Model model) {
        model.addAttribute("wahana", new Wahana());
        return "admin/wahana_form";
    }

    @PostMapping("/simpan")
    public String saveWahana(@ModelAttribute Wahana wahana, RedirectAttributes redirectAttributes) {
        try {
            Wahana savedWahana = wahanaService.saveWahana(wahana);
            redirectAttributes.addFlashAttribute("success", 
                "Wahana " + savedWahana.getNamaWahana() + " berhasil disimpan");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Gagal menyimpan wahana: " + e.getMessage());
        }
        return "redirect:/admin/wahana";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Wahana wahana = wahanaService.getWahanaById(id);
        if (wahana == null) {
            redirectAttributes.addFlashAttribute("error", "Wahana tidak ditemukan");
            return "redirect:/admin/wahana";
        }
        model.addAttribute("wahana", wahana);
        return "admin/wahana_form";
    }

    @GetMapping("/hapus/{id}")
    public String deleteWahana(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            wahanaService.deleteWahana(id);
            redirectAttributes.addFlashAttribute("success", "Wahana berhasil dihapus");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Gagal menghapus wahana: " + e.getMessage());
        }
        return "redirect:/admin/wahana";
    }
}