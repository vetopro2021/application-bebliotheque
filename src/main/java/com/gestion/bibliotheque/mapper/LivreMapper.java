package com.gestion.bibliotheque.mapper;

import com.gestion.bibliotheque.dto.LivreDto;
import com.gestion.bibliotheque.model.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class LivreMapper {

    @Autowired
    AuteurMapper auteurMapper;
    @Autowired
    EtudiantMapper etudiantMapper;

    public LivreDto mapFromLivre(Livre livre) {
        LivreDto dto = new LivreDto();
        dto.setIsbn(livre.getIsbn());
        dto.setTitre(livre.getTitre());
        dto.setDateDeSortie(String.valueOf(livre.getDateDeSortie()));
        dto.setDescription(livre.getDescription());
        dto.setDisponible(livre.isDisponible());
        dto.setDispoValue(dto.isDisponible() ? "Oui" : "Nom");
        if (livre.getAuteur() != null) {
            dto.setAuteurDto(auteurMapper.mapFromAuteur(livre.getAuteur()));
        }
        if (livre.getEtudiant() != null) {
            dto.setEtudiantDto(etudiantMapper.mapFromEtudiant(livre.getEtudiant()));
        }
        return dto;
    }

    public Livre mapFromLivreDto(LivreDto livreDto) {
        Livre livre = new Livre();
        livre.setIsbn(livreDto.getIsbn());
        livre.setTitre(livreDto.getTitre());
        livre.setDescription(livreDto.getDescription());
        livre.setDateDeSortie(LocalDate.parse(livreDto.getDateDeSortie()));
        livre.setDisponible(livreDto.isDisponible());
        if (livreDto.getAuteurDto() != null) {
            livre.setAuteur(auteurMapper.mapFromAuteurDto(livreDto.getAuteurDto()));
        }
        if (livreDto.getEtudiantDto() != null) {
            livre.setEtudiant(etudiantMapper.mapFromEtudiantDto(livreDto.getEtudiantDto()));
        }
        return livre;
    }

    public List<LivreDto> mapFromListLivres(List<Livre> livres) {
        List<LivreDto> livreDtos = new ArrayList<>();
        for (Livre livre : livres) {
            livreDtos.add(mapFromLivre(livre));
        }
        return livreDtos;
    }

    public List<Livre> mapFromListLivreDtos(List<LivreDto> livreDtos) {
        List<Livre> livres = new ArrayList<>();
        for (LivreDto livreDto : livreDtos) {
            livres.add(mapFromLivreDto(livreDto));
        }
        return livres;
    }
}
