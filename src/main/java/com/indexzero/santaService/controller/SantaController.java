package com.indexzero.santaservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SantaController {

    @GetMapping("/santa")
    public String getSantaPage() {
        return "santa-claus";
    }
}
