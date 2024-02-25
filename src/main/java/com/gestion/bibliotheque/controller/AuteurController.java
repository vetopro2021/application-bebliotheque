package com.gestion.bibliotheque.controller;

import com.gestion.bibliotheque.dto.AuteurDto;
import com.gestion.bibliotheque.service.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/auteur")
public class AuteurController {

    @Autowired
    private AuteurService auteurService;

    @GetMapping("/")
    public String initController() {
        return "Welcome to Auteur Controller";
    }

    @GetMapping("/formulaire")
    public String formulaireAuteur() {
        return "ajoutAuteur";
    }
    @PostMapping("/enregistrer")
    public String enregistrerAuteur(@ModelAttribute AuteurDto auteurDto) {
        auteurService.saveAuteur(auteurDto);
        return "redirect:/auteur/liste";
    }

    @GetMapping(value = "/liste")
    public String recupererListeAuteurs(Model model) {
        List<AuteurDto> auteurDtos = auteurService.recupérerTousLesAuteurs();
        model.addAttribute("auteurs", auteurDtos);
        return "auteurs";
    }
}
