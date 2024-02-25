package com.gestion.bibliotheque.dto;

import com.gestion.bibliotheque.model.Auteur;

import java.time.LocalDate;

public class LivreDto {
    private String isbn;
    private String titre;
    private String dateDeSortie;
    private String description;
    private boolean disponible;
    private String dispoValue;

    private Long auteurId;

    private Long etudiantId;

    private AuteurDto auteurDto;
    private EtudiantDto etudiantDto;

    public LivreDto() {

    }
    public LivreDto(String isbn, String titre, String dateDeSortie, String description, boolean disponible) {
        this.isbn = isbn;
        this.titre = titre;
        this.dateDeSortie = dateDeSortie;
        this.description= description;
        this.disponible = disponible;
    }

    public LivreDto(String isbn, String titre, String dateDeSortie, AuteurDto auteurDto, EtudiantDto etudiantDto) {
        this.isbn = isbn;
        this.titre = titre;
        this.dateDeSortie = dateDeSortie;
        this.auteurDto = auteurDto;
        this.etudiantDto = etudiantDto;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDateDeSortie() {
        return dateDeSortie;
    }

    public void setDateDeSortie(String dateDeSortie) {
        this.dateDeSortie = dateDeSortie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public AuteurDto getAuteurDto() {
        return auteurDto;
    }

    public void setAuteurDto(AuteurDto auteurDto) {
        this.auteurDto = auteurDto;
    }

    public EtudiantDto getEtudiantDto() {
        return etudiantDto;
    }

    public void setEtudiantDto(EtudiantDto etudiantDto) {
        this.etudiantDto = etudiantDto;
    }

    public String getDispoValue() {
        return dispoValue;
    }
    public void setDispoValue(String dispoValue) {
        this.dispoValue = dispoValue;
    }

    public Long getAuteurId() {
        return auteurId;
    }

    public void setAuteurId(Long auteurId) {
        this.auteurId = auteurId;
    }

    public Long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(Long etudiantId) {
        this.etudiantId = etudiantId;
    }
}
