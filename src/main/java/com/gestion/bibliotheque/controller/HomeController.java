package com.gestion.bibliotheque.controller;

import com.gestion.bibliotheque.dto.LivreDto;
import com.gestion.bibliotheque.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private LivreService livreService;
    @GetMapping("/home")
    public String homeBibliotheque(Model model) {
        List<LivreDto> livreDtos = livreService.recupererTousLivres();
        model.addAttribute("livres", livreDtos);
        return "index";
    }
}
