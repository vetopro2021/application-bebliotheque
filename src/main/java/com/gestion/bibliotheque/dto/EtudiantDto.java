package com.gestion.bibliotheque.dto;

public class EtudiantDto {

    private long id;
    private String cne;
    private String nom;
    private String prenom;
    private int age;
    private String email;
    private String dateEntreeEcole;
    private String anneeScolaireActuelle;

    public EtudiantDto() {
    }

    public EtudiantDto(String cne, String nom, String prenom, int age, String email, String dateEntreeEcole, String anneeScolaireActuelle) {
        this.cne = cne;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.email = email;
        this.dateEntreeEcole = dateEntreeEcole;
        this.anneeScolaireActuelle = anneeScolaireActuelle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateEntreeEcole() {
        return dateEntreeEcole;
    }

    public void setDateEntreeEcole(String dateEntreeEcole) {
        this.dateEntreeEcole = dateEntreeEcole;
    }

    public String getAnneeScolaireActuelle() {
        return anneeScolaireActuelle;
    }

    public void setAnneeScolaireActuelle(String anneeScolaireActuelle) {
        this.anneeScolaireActuelle = anneeScolaireActuelle;
    }
}
