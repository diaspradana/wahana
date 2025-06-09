package com.example.wahana.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String aboutPage() {
        return "aboutus"; // akan membuka aboutus.html di folder templates
    }
}
