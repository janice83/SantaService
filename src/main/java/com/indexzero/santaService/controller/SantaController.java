package com.indexzero.santaService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/santa")
@Controller
public class SantaController {

    @GetMapping("")
    public String getSantaPage() {
        return "santa-claus";
    }
}
