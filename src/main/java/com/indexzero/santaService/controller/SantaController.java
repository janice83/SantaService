package com.indexzero.santaService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SantaController {

    @GetMapping("/santa")
    public String getSantaPage() {
        return "redirect:/login";
    }
}
