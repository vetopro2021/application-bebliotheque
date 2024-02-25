package com.gestion.bibliotheque.mapper;

import com.gestion.bibliotheque.dto.EtudiantDto;
import com.gestion.bibliotheque.model.Etudiant;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EtudiantMapper {

    public EtudiantDto mapFromEtudiant(Etudiant etudiant) {
        EtudiantDto dto = new EtudiantDto();
        dto.setId(etudiant.getId());
        dto.setCne(etudiant.getCne());
        dto.setAge(etudiant.getAge());
        dto.setEmail(etudiant.getEmail());
        dto.setNom(etudiant.getNom());
        dto.setPrenom(etudiant.getPrenom());
        dto.setAnneeScolaireActuelle(etudiant.getAnneeScolaireActuelle());
        dto.setDateEntreeEcole(String.valueOf(etudiant.getDateEntreeEcole()));
        return dto;
    }

    public Etudiant mapFromEtudiantDto(EtudiantDto etudiantDto) {
        Etudiant etudiant = new Etudiant();
        etudiant.setCne(etudiantDto.getCne());
        etudiant.setNom(etudiantDto.getNom());
        etudiant.setPrenom(etudiantDto.getPrenom());
        etudiant.setEmail(etudiantDto.getEmail());
        etudiant.setAge(etudiantDto.getAge());
        etudiant.setAnneeScolaireActuelle(etudiantDto.getAnneeScolaireActuelle());
        etudiant.setDateEntreeEcole(LocalDate.parse(etudiantDto.getDateEntreeEcole()));
        return etudiant;
    }

    public List<EtudiantDto> mapFromListEtudiants(List<Etudiant> etudiants) {
        List<EtudiantDto> etudiantDtos = new ArrayList<>();
        for(Etudiant etudiant: etudiants) {
            etudiantDtos.add(mapFromEtudiant(etudiant));
        }
        return etudiantDtos;
    }

    public List<Etudiant> mapFromListEtudiantDtos(List<EtudiantDto> etudiantDtos) {
        List<Etudiant> etudiants = new ArrayList<>();
        for(EtudiantDto etudiantDto: etudiantDtos) {
            etudiants.add(mapFromEtudiantDto(etudiantDto));
        }
        return etudiants;
    }
}
