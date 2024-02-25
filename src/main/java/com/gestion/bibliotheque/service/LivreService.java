package com.gestion.bibliotheque.service;

import com.gestion.bibliotheque.dto.LivreDto;

import java.util.List;

public interface LivreService {
    void saveLivre(LivreDto livreDto);
    void updateLivre(LivreDto livreDto);
    LivreDto recupererLivreByISBNOrTitre(String value);
    List<LivreDto> rechercherLivresParTitre(String titre);
    List<LivreDto> rechercherLivresParAuteur(String nomAuteur, String prenomAuteur);
    void affecterLivreAEtudiant(String livreId, Long etudiantId);
    List<LivreDto> recupererTousLivres();
    boolean deleteLivre(String isbn);

}
