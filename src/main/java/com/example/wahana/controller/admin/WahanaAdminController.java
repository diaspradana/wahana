package com.example.wahana.controller.admin;

import com.example.wahana.model.entity.Wahana;
import com.example.wahana.model.service.WahanaService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/wahana")
public class WahanaAdminController {

    private final WahanaService wahanaService;

    @Autowired
    public WahanaAdminController(WahanaService wahanaService) {
        this.wahanaService = wahanaService;
    }

    // Gabungkan kedua method menjadi satu
    @GetMapping
    public String listWahana(
            @RequestParam(required = false) String kategori, 
            Model model) {
        
        model.addAttribute("wahanaList", wahanaService.getWahanaByKategori(kategori));
        model.addAttribute("kategoriTerpilih", kategori); // Untuk mempertahankan pilihan filter
        return "admin/wahana_admin";
    }

    @GetMapping("/tambah")
    public String showAddForm(Model model) {
        model.addAttribute("wahana", new Wahana());
        return "admin/wahana_form";
    }
    
    

    @PostMapping("/simpan")
    public String saveWahana(
            @ModelAttribute Wahana wahana,
            @RequestParam("gambarFile") MultipartFile gambarFile,
            RedirectAttributes redirectAttributes) {

        try {
            String folderPath = "uploads/wahana";
            java.nio.file.Path folder = java.nio.file.Paths.get(folderPath);
            java.nio.file.Files.createDirectories(folder); // Buat folder jika belum ada

            if (!gambarFile.isEmpty()) {
                // Jika ini adalah update dan wahana sudah punya gambar lama
                if (wahana.getId() != null) {
                    Wahana existing = wahanaService.getWahanaById(wahana.getId());
                    if (existing != null && existing.getGambar() != null) {
                        java.nio.file.Path oldFile = folder.resolve(existing.getGambar());
                        java.nio.file.Files.deleteIfExists(oldFile); // Hapus gambar lama
                    }
                }

                // Simpan file gambar baru
                String fileName = java.util.UUID.randomUUID() + "_" + gambarFile.getOriginalFilename();
                java.nio.file.Path filePath = folder.resolve(fileName);
                java.nio.file.Files.copy(gambarFile.getInputStream(), filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                // Set nama file gambar ke entitas
                wahana.setGambar(fileName);
            } else if (wahana.getId() != null) {
                // Jika tidak upload gambar baru, gunakan gambar lama
                Wahana existing = wahanaService.getWahanaById(wahana.getId());
                if (existing != null) {
                    wahana.setGambar(existing.getGambar());
                }
            }

            Wahana savedWahana = wahanaService.saveWahana(wahana);
            redirectAttributes.addFlashAttribute("success",
                    "Wahana " + savedWahana.getNamaWahana() + " berhasil disimpan");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Gagal menyimpan wahana: " + e.getMessage());
            e.printStackTrace(); // Logging untuk debug
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