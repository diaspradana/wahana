package com.example.wahana.controller.user;

import com.example.wahana.model.entity.Wahana;
import com.example.wahana.model.service.WahanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user/tiket")
public class TiketDetailController {

    @Autowired
    private WahanaService wahanaService;

    @GetMapping
    public String viewTiket(@RequestParam(required = false) String keyword, 
                           @RequestParam(defaultValue = "id") String sortBy, 
                           @RequestParam(defaultValue = "asc") String sortDir,
                           Model model) {
        // Get filtered and sorted list of wahanas
        model.addAttribute("wahanaList", wahanaService.cariWahana(keyword, sortBy, sortDir));
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", "asc".equals(sortDir) ? "desc" : "asc");
        
        return "user/tiket";
    }

    @GetMapping("/detail/{id}")
    public String viewTiketDetail(@PathVariable Long id, Model model) {
        Wahana wahana = wahanaService.getWahanaById(id);
        if (wahana == null) {
            model.addAttribute("error", "Wahana tidak ditemukan");
            return "redirect:/user/tiket";
        }
        
        model.addAttribute("wahana", wahana);
        return "user/tiket_detail";
    }

    @Controller
@RequestMapping("/user/tiket")
public class TiketController {
    // ...
    
    @GetMapping
    public String viewTiket(/* ... */) {
        // ...
        return "user/tiket";  // Mengarah ke templates/user/tiket.html
    }

    @GetMapping("/detail/{id}")
    public String viewTiketDetail(/* ... */) {
        // ...
        return "user/tiket_detail";  // Mengarah ke templates/user/tiket_detail.html
    }
}
}