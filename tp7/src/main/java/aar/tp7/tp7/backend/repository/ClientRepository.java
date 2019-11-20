package aar.tp7.tp7.backend.repository;

import aar.tp7.tp7.backend.modele.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findById(long id);
    Iterable<Client> findByNom(String nom);
}
