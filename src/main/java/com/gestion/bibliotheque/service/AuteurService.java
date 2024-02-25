package com.gestion.bibliotheque.service;

import com.gestion.bibliotheque.dto.AuteurDto;

import java.util.List;

public interface AuteurService {

    public void saveAuteur(AuteurDto auteurDto);

    public List<AuteurDto> recupérerTousLesAuteurs();
}
