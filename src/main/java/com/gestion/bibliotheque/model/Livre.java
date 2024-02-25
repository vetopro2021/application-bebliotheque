package com.gestion.bibliotheque.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String isbn;
    private String titre;

    private String description;
    private LocalDate dateDeSortie;
    private boolean disponible;
    @ManyToOne
    @JoinColumn(name = "auteur_id")
    private Auteur auteur;
    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    public Livre() {

    }

    public Livre(String isbn, String titre, LocalDate dateDeSortie, String description, boolean disponible) {
        this.isbn = isbn;
        this.titre = titre;
        this.dateDeSortie = dateDeSortie;
        this.description = description;
        this.disponible = disponible;
    }

    public Livre(String isbn, String titre, LocalDate dateDeSortie, boolean disponible, Auteur auteur, Etudiant etudiant) {
        this.isbn = isbn;
        this.titre = titre;
        this.dateDeSortie = dateDeSortie;
        this.disponible = disponible;
        this.auteur = auteur;
        this.etudiant = etudiant;
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

    public LocalDate getDateDeSortie() {
        return dateDeSortie;
    }

    public void setDateDeSortie(LocalDate dateDeSortie) {
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

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
}

