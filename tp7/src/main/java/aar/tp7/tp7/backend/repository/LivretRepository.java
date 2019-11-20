package aar.tp7.tp7.backend.repository;

import aar.tp7.tp7.backend.modele.Livret;
import org.springframework.data.repository.CrudRepository;

public interface LivretRepository extends CrudRepository<Livret, Long> {
    Livret findById(long id);
}