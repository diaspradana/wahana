package com.example.wahana.controller.admin;

import com.example.wahana.model.entity.Wahana;
import com.example.wahana.model.service.WahanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/wahana")
public class WahanaAdminController {
    @Autowired
    private WahanaService wahanaService;

    @GetMapping
    public String listWahana(@RequestParam(required = false) String keyword, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String sortDir, Model model){
        model.addAttribute("wahanaList", wahanaService.cariWahana(keyword, sortBy, sortDir));
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", "asc".equals(sortDir) ? "desc" : "asc");

        return "wahana/index";
    }

    @GetMapping("/add")
    public String addWahanaForm(Model model){
        model.addAttribute("wahana", new Wahana());
        model.addAttribute("isEdit", false);
        return "wahana/add";
    }

    @PostMapping("/add")
    public String saveWahana(@ModelAttribute Wahana wahana, Model model, RedirectAttributes redirectAttributes){
        if (wahana.getNamaWahana() == null || wahana.getNamaWahana().trim().isEmpty()){
             model.addAttribute("error","Nama wahana tidak boleh kosong");
             model.addAttribute("wahana", wahana);
             model.addAttribute("isEdit",false);
             return "wahana/add";
        }

        // Save wahana
        wahanaService.saveWahana(wahana);
        redirectAttributes.addFlashAttribute("success","Wahana berhasil ditambahkan");
        return "redirect:/wahana";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        Wahana wahana = wahanaService.getWahanaByID(id);
        if(wahana == null){
            model.addAttribute("error","Wahana dengan ID " + id + "tidak ditemukan");
            return "redirect/wahana";
        }

        model.addAttribute("wahana", wahana);
        model.addAttribute("isEdit", true);
        return "wahana/add";
    }

    @PostMapping("/edit/{id}")
    public String editWahana(@PathVariable Long id, @ModelAttribute Wahana wahana,
                              Model model, RedirectAttributes redirectAttributes) {

        if (wahana.getNamaWahana() == null || wahana.getNamaWahana().trim().isEmpty()) {
            model.addAttribute("error", "Nama wahana tidak boleh kosong");
            model.addAttribute("wahana", wahana);
            model.addAttribute("isEdit", true);
            return "wahana/add";
        }

        wahana.setId(id);
        wahanaService.updateWahana(wahana);
        redirectAttributes.addFlashAttribute("success", "Wahana berhasil diperbarui");
        return "redirect:/wahana";
    }

    @GetMapping("/delete/{id}")
    public String deleteWahana(@PathVariable Long id, RedirectAttributes redirectAttributes){
        Wahana wahana = wahanaService.getWahanaByID(id);
        if (wahana != null){
            wahanaService.deleteWahana(id);
            redirectAttributes.addFlashAttribute("success","Wahana" + wahana.getNamaWahana() + "berhasil dihapus");
        } else {
            redirectAttributes.addFlashAttribute("error","Wahana dengan ID " + id + " tidak ditemukan");
        }
        return "redirect:/wahana";
    }
}
