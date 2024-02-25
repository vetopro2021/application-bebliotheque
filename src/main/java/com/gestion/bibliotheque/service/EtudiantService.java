package com.gestion.bibliotheque.service;

import com.gestion.bibliotheque.dto.EtudiantDto;

import java.util.List;

public interface EtudiantService {
    void saveEtudiant(EtudiantDto etudiantDto);
    List<EtudiantDto> recupererEtudiants();
    EtudiantDto recupererEtudiantParCneOuNomOuPrenom(String value);

    void updateEtudiant(EtudiantDto etudiantDto);

    boolean deleteEtudiant(String cne);
}
