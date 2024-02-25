package com.gestion.bibliotheque.repository;

import com.gestion.bibliotheque.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
    List<Livre> findByTitreContainingIgnoreCase(String titre);
    Livre findByIsbn(String isbn);
    List<Livre> findByAuteur_NomAndAuteur_PrenomAllIgnoreCase(String nomAuteur, String prenomAuteur);
    @Query("SELECT l FROM Livre l WHERE l.isbn = :value " +
            "OR UPPER(l.titre) like UPPER(concat('%',:value,'%')) ")
    Livre findByExactIsbnOrExactTitre(String value);
}
