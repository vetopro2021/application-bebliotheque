package com.gestion.bibliotheque.service.impl;

import com.gestion.bibliotheque.dto.EtudiantDto;
import com.gestion.bibliotheque.mapper.EtudiantMapper;
import com.gestion.bibliotheque.model.Etudiant;
import com.gestion.bibliotheque.repository.EtudiantRepository;
import com.gestion.bibliotheque.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EtudiantServiceImpl implements EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private EtudiantMapper etudiantMapper;


    @Override
    public void saveEtudiant(EtudiantDto etudiantDto) {
        if(etudiantDto == null) {
            return;
        }
        try {
            etudiantRepository.save(etudiantMapper.mapFromEtudiantDto(etudiantDto));
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<EtudiantDto> recupererEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        if(etudiants.isEmpty()) {
            return new ArrayList<>();
        }
        return etudiantMapper.mapFromListEtudiants(etudiants);
    }

    @Override
    public EtudiantDto recupererEtudiantParCneOuNomOuPrenom(String value) {
        Etudiant etudiant = etudiantRepository.findExactByCneOrByNomOrByPrenom(value);
        if(etudiant != null) {
            return etudiantMapper.mapFromEtudiant(etudiantRepository.findExactByCneOrByNomOrByPrenom(value));
        }
        return new EtudiantDto();
    }

    @Override
    public void updateEtudiant(EtudiantDto etudiantDto) {
        if(etudiantDto != null) {
            Etudiant etudiant = etudiantRepository.findExactByCneOrByNomOrByPrenom(etudiantDto.getCne());
            if(etudiant != null) {
                etudiant.setNom(etudiantDto.getNom());
                etudiant.setPrenom(etudiantDto.getPrenom());
                etudiant.setAge(etudiantDto.getAge());
                etudiant.setEmail(etudiantDto.getEmail());
                etudiant.setDateEntreeEcole(LocalDate.parse(etudiantDto.getDateEntreeEcole()));
                etudiant.setAnneeScolaireActuelle(etudiantDto.getAnneeScolaireActuelle());
                try {
                    etudiantRepository.saveAndFlush(etudiant);
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean deleteEtudiant(String cne) {
        Etudiant etudiant = etudiantRepository.findExactByCneOrByNomOrByPrenom(cne);
        if(etudiant != null) {
            try {
                etudiantRepository.delete(etudiant);
                return true;
            } catch (DataAccessException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
