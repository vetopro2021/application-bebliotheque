package com.gestion.bibliotheque.controller;

import com.gestion.bibliotheque.dto.EtudiantDto;
import com.gestion.bibliotheque.dto.LivreDto;
import com.gestion.bibliotheque.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/etudiant")
public class EtudiantController {

    @Autowired
    EtudiantService etudiantService;
    @GetMapping("/")
    public String initApp() {
        return "Welcome to Bibliothque Etudiant Apps";
    }

    @GetMapping("/formulaire")
    public String formulaireEtudiant() {
        return "ajoutEtudiant";
    }
    @PostMapping(value = "/enregistrer")
    public String saveEtudiant(@ModelAttribute EtudiantDto etudiantDto) {
        etudiantService.saveEtudiant(etudiantDto);
        return "redirect:/etudiant/liste";
    }

    @GetMapping(value = "/liste")
    public String recupererListeEtudiants(Model model) {
        List<EtudiantDto> etudiantDtos = etudiantService.recupererEtudiants();
        model.addAttribute("etudiants",etudiantDtos);
        return "etudiants";
    }

    @GetMapping(value="/par-cne-nom-prenom")
    public String recupererEtudiantParCneOuNomOuPrenom(@RequestParam String value) {
        EtudiantDto etudiantDto = etudiantService.recupererEtudiantParCneOuNomOuPrenom(value);
        return "";
    }

    @GetMapping("/{cne}")
    public String detailEtudiant(@PathVariable String cne, Model model) {
        EtudiantDto etudiantDto = etudiantService.recupererEtudiantParCneOuNomOuPrenom(cne);
        model.addAttribute("etudiant", etudiantDto);
        return "etudiantDetail";
    }

    @GetMapping("/modifier/{cne}")
    public String modifierEtudiant(@PathVariable String cne, Model model) {
        EtudiantDto etudiant = etudiantService.recupererEtudiantParCneOuNomOuPrenom(cne);
        model.addAttribute("etudiant", etudiant);
        return "formulaireModificationEtudiant";
    }

    @GetMapping("/supprimer/{cne}")
    public String supprimerEtudiant(@PathVariable String cne, RedirectAttributes redirectAttributes) {
        boolean result = etudiantService.deleteEtudiant(cne);
        if (result) {
            // Ajouter un attribut flash pour indiquer la réussite de l'opération
            redirectAttributes.addFlashAttribute("successMessage", "L'étudiant a été supprimé avec succès.");
        } else {
            // Ajouter un attribut flash pour indiquer l'échec de l'opération
            redirectAttributes.addFlashAttribute("errorMessage", "La suppression de l'étudiant a échoué.");
        }

        // Rediriger vers la liste des étudiants
        return "redirect:/etudiant/liste";
    }

    @PostMapping("/modifier")
    public String modifierEtudiant(@ModelAttribute EtudiantDto etudiantDto) {
        etudiantService.updateEtudiant(etudiantDto);
        return "redirect:/etudiant/liste";
    }
}
