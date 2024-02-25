package com.gestion.bibliotheque.controller;

import com.gestion.bibliotheque.dto.AuteurDto;
import com.gestion.bibliotheque.dto.EtudiantDto;
import com.gestion.bibliotheque.dto.LivreDto;
import com.gestion.bibliotheque.service.AuteurService;
import com.gestion.bibliotheque.service.EtudiantService;
import com.gestion.bibliotheque.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/livre")
public class LivreController {

    @Autowired
    private LivreService livreService;
    @Autowired
    private AuteurService auteurService;
    @Autowired
    EtudiantService etudiantService;

    @GetMapping("/")
    public String initController() {
        return "Welcome to livre Controller";
    }

    @GetMapping("/formulaire")
    public String formulaireLivre(Model model) {
        List<AuteurDto> auteurs = auteurService.recupérerTousLesAuteurs();
        model.addAttribute("listeAuteurs", auteurs);
        return "ajoutLivre";
    }

    @PostMapping("/enregistrer")
    public String enregistrerLivre(@ModelAttribute LivreDto livreDto) {
        livreService.saveLivre(livreDto);
        return "redirect:/livre/liste";
    }

    @GetMapping("/par-titre")
    public ResponseEntity<List<LivreDto>> rechercherLivresParTitre(@RequestParam String titre) {
        List<LivreDto> livresTrouves = livreService.rechercherLivresParTitre(titre);
        return new ResponseEntity<>(livresTrouves, HttpStatus.OK);
    }

    @GetMapping("/par-auteur")
    public ResponseEntity<List<LivreDto>> rechercherLivresParAuteur(
            @RequestParam String nomAuteur,
            @RequestParam String prenomAuteur) {
        List<LivreDto> livresTrouves = livreService.rechercherLivresParAuteur(nomAuteur, prenomAuteur);
        return new ResponseEntity<>(livresTrouves, HttpStatus.OK);
    }

    @PostMapping("/affecter")
    public ResponseEntity<LivreDto> affecterLivreAEtudiant(@RequestParam String livreId, @RequestParam Long etudiantId) {
        livreService.affecterLivreAEtudiant(livreId, etudiantId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/liste")
    public String recupererTousLesLivres(Model model) {
        List<LivreDto> livreDtos = livreService.recupererTousLivres();
        model.addAttribute("livres", livreDtos);
        return "livres";
    }
    @GetMapping("/par-isbn-titre")
    public ResponseEntity<LivreDto> recupererLivreParIsbnOuTitre(@RequestParam String value) {
        LivreDto body = livreService.recupererLivreByISBNOrTitre(value);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/{isbn}")
    public String recupererLivreParIsbn(@PathVariable String isbn, Model model) {
        LivreDto body = livreService.recupererLivreByISBNOrTitre(isbn);
        model.addAttribute("livre", body);
        return "livreDetail";
    }
    @GetMapping(value = "/affectation")
    public String recupererListeEtudiants(Model model) {
        System.out.println("Test affectation");
        model.addAttribute("etudiants", etudiantService.recupererEtudiants());
        return "affectation";
    }

    @GetMapping("/modifier/{isbn}")
    public String afficherFormulaireModification(@PathVariable String isbn, Model model) {
        // Récupérez le livre à modifier en fonction de l'ISBN
        LivreDto livre = livreService.recupererLivreByISBNOrTitre(isbn);
        // Récupérer la liste des auteurs depuis le service
        List<AuteurDto> listeAuteurs = auteurService.recupérerTousLesAuteurs();

        // Récupérer la liste des étudiants depuis le service
        List<EtudiantDto> listeEtudiants = etudiantService.recupererEtudiants();

        if(livre.getAuteurDto() != null) {
            model.addAttribute("listeAuteurs", listeAuteurs);
        }
        if(livre.getEtudiantDto() != null) {
            model.addAttribute("listeEtudiants", listeEtudiants);
        }
        // Ajoutez le livre au modèle
        model.addAttribute("livre", livre);
        // Ajoutez d'autres données nécessaires au modèle si nécessaire
        // Retournez le nom de la page Thymeleaf (HTML) du formulaire de modification
        return "formulaireModificationLivre";
    }

    @PostMapping("/modifier")
    public String modifierLivre(@ModelAttribute LivreDto livreDto) {
        livreService.updateLivre(livreDto);
        return "redirect:/livre/liste";
    }

    @GetMapping("/supprimer/{isbn}")
    public String supprimerLivre(@PathVariable String isbn, RedirectAttributes redirectAttributes) {
        boolean result = livreService.deleteLivre(isbn);
        if (result) {
            // Ajouter un attribut flash pour indiquer la réussite de l'opération
            redirectAttributes.addFlashAttribute("successMessage", "Le livre a été supprimé avec succès.");
        } else {
            // Ajouter un attribut flash pour indiquer l'échec de l'opération
            redirectAttributes.addFlashAttribute("errorMessage", "La suppression du livre a échoué.");
        }

        // Rediriger vers la liste des étudiants
        return "redirect:/livre/liste";
    }
}