package com.gestion.bibliotheque.repository;

import com.gestion.bibliotheque.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    @Query("SELECT e FROM Etudiant e WHERE UPPER(e.cne) like UPPER(concat('%',:value,'%')) " +
            "OR UPPER(e.nom) like UPPER(concat('%',:value,'%')) " +
            "OR UPPER(e.prenom) like UPPER(concat('%',:value,'%'))")
    Etudiant findExactByCneOrByNomOrByPrenom(String value);
}
