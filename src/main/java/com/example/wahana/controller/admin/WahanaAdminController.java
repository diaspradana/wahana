package com.example.wahana.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.wahana.model.entity.Wahana;
import com.example.wahana.model.service.AdminService;

@Controller
@RequestMapping("/admin/wahana")
public class WahanaAdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String listWahana(Model model) {
        model.addAttribute("wahanaList", adminService.getAllWahana());
        return "admin/wahana_list";
    }

    @GetMapping("/tambah")
    public String formTambahWahana(Model model) {
        model.addAttribute("wahana", new Wahana());
        return "admin/wahana_form";
    }

    @PostMapping("/simpan")
    public String simpanWahana(@ModelAttribute Wahana wahana) {
        adminService.simpanWahana(wahana);
        return "redirect:/admin/wahana";
    }

    @GetMapping("/hapus/{id}")
    public String hapusWahana(@PathVariable Long id) {
        adminService.hapusWahana(id);
        return "redirect:/admin/wahana";
    }
}