package aar.tp7.tp7.backend.repository;

import aar.tp7.tp7.backend.modele.Compte;
import org.springframework.data.repository.CrudRepository;

public interface CompteRepository extends CrudRepository<Compte, Long> {
    Compte findById(long id);
}