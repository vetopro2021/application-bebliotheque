package com.gestion.bibliotheque.service.impl;

import com.gestion.bibliotheque.dto.AuteurDto;
import com.gestion.bibliotheque.mapper.AuteurMapper;
import com.gestion.bibliotheque.model.Auteur;
import com.gestion.bibliotheque.repository.AuteurRepository;
import com.gestion.bibliotheque.service.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuteurServiceImpl implements AuteurService {

    @Autowired
    private AuteurRepository auteurRepository;
    @Autowired
    private AuteurMapper auteurMapper;

    @Override
    public void saveAuteur(AuteurDto auteurDto) {
        if (auteurDto != null) {
            auteurRepository.save(auteurMapper.mapFromAuteurDto(auteurDto));
        }
    }

    @Override
    public List<AuteurDto> recupérerTousLesAuteurs() {
        List<Auteur> auteurs = auteurRepository.findAll();
        return auteurMapper.mapFromListAuteurs(auteurs);
    }
}
