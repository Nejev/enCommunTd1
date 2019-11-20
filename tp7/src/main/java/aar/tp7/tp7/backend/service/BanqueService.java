package aar.tp7.tp7.backend.service;


import aar.tp7.tp7.backend.modele.Client;
import aar.tp7.tp7.backend.modele.Compte;
import aar.tp7.tp7.backend.modele.Livret;

public interface BanqueService {

    Client findClient(long id);
    Iterable<Client> findClient(String nom);
    Iterable<Client> findAllClient();
    Client saveClient(Client client);
    Iterable<Client> saveAllClient(Iterable<Client> clients);
    void deleteClient(long id);
    void deleteAllClient();

    Compte findCompte(long id);
    Iterable<Compte> findAllCompte();
    Compte saveCompte(Compte compte);
    Iterable<Compte> saveAllCompte(Iterable<Compte> comptes);
    void deleteCompte(long id);
    void deleteAllCompte();

    Livret findLivret(long id);
    Iterable<Livret> findAllLivret();
    Compte saveLivret(Livret livret);
    Iterable<Livret> saveAllLivret(Iterable<Livret> livrets);
    void deleteLivret(long id);
    void deleteAllLivret();

    boolean virement(long id1, long id2, double montant);
}
