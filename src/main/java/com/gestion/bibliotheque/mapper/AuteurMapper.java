package com.gestion.bibliotheque.mapper;

import com.gestion.bibliotheque.dto.AuteurDto;
import com.gestion.bibliotheque.model.Auteur;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuteurMapper {

    public Auteur mapFromAuteurDto(AuteurDto auteurDto) {
        Auteur auteur = new Auteur();
        auteur.setNom(auteurDto.getNom());
        auteur.setPrenom(auteurDto.getPrenom());
        auteur.setNationalite(auteurDto.getNationalite());
        return auteur;
    }

    public AuteurDto mapFromAuteur(Auteur auteur) {
        AuteurDto dto = new AuteurDto();
        dto.setId(auteur.getId());
        dto.setNom(auteur.getNom());
        dto.setPrenom(auteur.getPrenom());
        dto.setNationalite(auteur.getNationalite());
        return dto;
    }

    public List<AuteurDto> mapFromListAuteurs(List<Auteur> auteurs) {
        List<AuteurDto> auteurDtos = new ArrayList<>();
        if (CollectionUtils.isEmpty(auteurs)) {
            return null;
        }
        for (Auteur auteur : auteurs) {
            AuteurDto dto = new AuteurDto();
            dto.setId(auteur.getId());
            dto.setNom(auteur.getNom());
            dto.setPrenom(auteur.getPrenom());
            dto.setNationalite(auteur.getNationalite());
            auteurDtos.add(dto);
        }
        return auteurDtos;
    }

    public List<Auteur> mapFromListAuteursDtos(List<AuteurDto> auteurDtos) {
        List<Auteur> auteurs = new ArrayList<>();
        if (CollectionUtils.isEmpty(auteurDtos)) {
            return null;
        }
        for (AuteurDto auteurDto : auteurDtos) {
            Auteur auteur = new Auteur();
            auteur.setNom(auteurDto.getNom());
            auteur.setPrenom(auteurDto.getPrenom());
            auteur.setNationalite(auteurDto.getNationalite());
            auteurs.add(auteur);
        }
        return auteurs;
    }
}
