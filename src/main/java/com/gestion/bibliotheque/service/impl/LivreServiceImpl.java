package com.gestion.bibliotheque.service.impl;

import com.gestion.bibliotheque.dto.LivreDto;
import com.gestion.bibliotheque.mapper.LivreMapper;
import com.gestion.bibliotheque.model.Auteur;
import com.gestion.bibliotheque.model.Etudiant;
import com.gestion.bibliotheque.model.Livre;
import com.gestion.bibliotheque.repository.AuteurRepository;
import com.gestion.bibliotheque.repository.EtudiantRepository;
import com.gestion.bibliotheque.repository.LivreRepository;
import com.gestion.bibliotheque.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LivreServiceImpl implements LivreService {

    @Autowired
    private LivreRepository livreRepository;
    @Autowired
    private AuteurRepository auteurRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private LivreMapper livreMapper;
    @Override
    public void saveLivre(LivreDto livreDto) {
        if(livreDto != null) {
            Livre livre = livreMapper.mapFromLivreDto(livreDto);
            try {
                if(livreDto.getAuteurId() != null) {
                    Optional<Auteur> auteur = auteurRepository.findById(livreDto.getAuteurId());
                    auteur.ifPresent(livre::setAuteur);
                }
                if(livreDto.getEtudiantDto() != null) {
                    Optional<Etudiant> etudiant = etudiantRepository.findById(livreDto.getEtudiantId());
                    etudiant.ifPresent(livre::setEtudiant);
                }
                livreRepository.save(livre);
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void updateLivre(LivreDto livreDto) {
        if(livreDto != null) {
            Livre livre = livreRepository.findByIsbn(livreDto.getIsbn());
            livre.setTitre(livreDto.getTitre());
            livre.setDescription(livreDto.getDescription());
            if(livreDto.getAuteurId() != null) {
                Optional<Auteur> auteur = auteurRepository.findById(livreDto.getAuteurId());
                livre.setAuteur(auteur.get());
            }
            if(livreDto.getEtudiantId() != null) {
                Optional<Etudiant> etudiant = etudiantRepository.findById(livreDto.getEtudiantId());
                livre.setEtudiant(etudiant.get());
            }
            try {
                livreRepository.saveAndFlush(livre);
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public LivreDto recupererLivreByISBNOrTitre(String value) {
        Livre livre = livreRepository.findByExactIsbnOrExactTitre(value);
        if(livre != null) {
            return livreMapper.mapFromLivre(livre);
        }
        return new LivreDto();
    }

    @Override
    public List<LivreDto> rechercherLivresParTitre(String titre) {
        List<Livre> livres = livreRepository.findByTitreContainingIgnoreCase(titre);
        if(CollectionUtils.isEmpty(livres)) {
            return new ArrayList<>();
        }
        return livreMapper.mapFromListLivres(livres);
    }

    @Override
    public List<LivreDto> rechercherLivresParAuteur(String nomAuteur, String prenomAuteur) {
        List<Livre> livres =livreRepository.findByAuteur_NomAndAuteur_PrenomAllIgnoreCase(nomAuteur, prenomAuteur);
        if(CollectionUtils.isEmpty(livres)) {
            return new ArrayList<>();
        }
        return livreMapper.mapFromListLivres(livres);
    }

    @Override
    public void affecterLivreAEtudiant(String livreId, Long etudiantId) {
        Livre livre = livreRepository.findByIsbn(livreId);
        if(livre != null) {
            Etudiant etudiant = etudiantRepository.findById(etudiantId)
                    .orElseThrow(() -> new EntityNotFoundException("Étudiant non trouvé avec l'ID : " + etudiantId));
            livre.setDisponible(false);
            livre.setEtudiant(etudiant);
            try {
                livreRepository.save(livre);
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<LivreDto> recupererTousLivres() {
        List<Livre> livres = livreRepository.findAll();
        if (livres.isEmpty()) {
            return new ArrayList<>();
        }
        return livreMapper.mapFromListLivres(livres);
    }

    @Override
    public boolean deleteLivre(String isbn) {
        Livre livre = livreRepository.findByIsbn(isbn);
        if(livre != null) {
            try {
                livreRepository.delete(livre);
                return true;
            } catch (DataAccessException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
