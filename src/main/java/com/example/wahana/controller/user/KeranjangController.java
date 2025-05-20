// package com.example.wahana.controller.user;

// import com.example.wahana.model.entity.Pemesanan;
// import com.example.wahana.model.entity.Wahana;
// import com.example.wahana.model.service.PemesananService;
// import com.example.wahana.model.service.WahanaService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;

// import java.security.Principal;
// import java.util.List;

// @Controller
// @RequestMapping("/user/keranjang")
// public class KeranjangController {

//     @Autowired
//     private PemesananService pemesananService;
    
//     @Autowired
//     private WahanaService wahanaService;

//     @GetMapping
//     public String viewKeranjang(Model model, Principal principal) {
//         if (principal == null) {
//             return "redirect:/login";
//         }
        
//         String username = principal.getName();
//         List<Pemesanan> items = pemesananService.getPemesananByUser(username);
//         model.addAttribute("keranjangItems", items);
        
//         double total = items.stream()
//                 .mapToDouble(p -> p.getWahana().getHarga() * p.getJumlah())
//                 .sum();
//         model.addAttribute("totalHarga", total);
        
//         return "user/keranjang";
//     }

//     @PostMapping("/add/{wahanaId}")
//     public String addToKeranjang(@PathVariable Long wahanaId, 
//                                @RequestParam(defaultValue = "1") int jumlah,
//                                Principal principal) {
//         if (principal == null) {
//             return "redirect:/login";
//         }
        
//         Wahana wahana = wahanaService.getWahanaById(wahanaId);
//         if (wahana == null) {
//             return "redirect:/user/tiket?error=wahana_not_found";
//         }
        
//         pemesananService.addToKeranjang(principal.getName(), wahana, jumlah);
//         return "redirect:/user/tiket";
//     }

//     @PostMapping("/update/{id}")
//     public String updateKeranjang(@PathVariable Long id, 
//                                 @RequestParam int jumlah) {
//         pemesananService.updateJumlahPemesanan(id, jumlah);
//         return "redirect:/user/keranjang";
//     }

//     @GetMapping("/remove/{id}")
//     public String removeFromKeranjang(@PathVariable Long id) {
//         pemesananService.removeFromKeranjang(id);
//         return "redirect:/user/keranjang";
//     }

//     @PostMapping("/checkout")
//     public String checkoutKeranjang(Principal principal) {
//         if (principal == null) {
//             return "redirect:/login";
//         }
        
//         pemesananService.checkout(principal.getName());
//         return "redirect:/user/history-pemesanan";
//     }
// }