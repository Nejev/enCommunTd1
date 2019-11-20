package aar.tp7.tp7.backend.service;


import aar.tp7.tp7.backend.modele.Client;
import aar.tp7.tp7.backend.modele.Compte;
import aar.tp7.tp7.backend.modele.Livret;
import aar.tp7.tp7.backend.repository.ClientRepository;
import aar.tp7.tp7.backend.repository.CompteRepository;
import aar.tp7.tp7.backend.repository.LivretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BanqueServiceImpl implements BanqueService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private LivretRepository livretRepository;

    @Override
    public Client findClient(long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Iterable<Client> findClient(String nom) {
        return clientRepository.findByNom(nom);
    }

    @Override
    public Iterable<Client> findAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Iterable<Client> saveAllClient(Iterable<Client> clients) {
        return clientRepository.saveAll(clients);
    }

    @Override
    public void deleteClient(long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void deleteAllClient() {
        clientRepository.deleteAll();
    }

    @Override
    public Compte findCompte(long id) {
        return compteRepository.findById(id);
    }

    @Override
    public Iterable<Compte> findAllCompte() {
        return compteRepository.findAll();
    }

    @Override
    public Compte saveCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    @Override
    public Iterable<Compte> saveAllCompte(Iterable<Compte> comptes) {
        return compteRepository.saveAll(comptes);
    }

    @Override
    public void deleteCompte(long id) {
        compteRepository.deleteById(id);
    }

    @Override
    public void deleteAllCompte() {
        compteRepository.deleteAll();
    }

    @Override
    public Livret findLivret(long id) {
        return livretRepository.findById(id);
    }

    @Override
    public Iterable<Livret> findAllLivret() {
        return livretRepository.findAll();
    }

    @Override
    public Compte saveLivret(Livret livret) {
        return livretRepository.save(livret);
    }

    @Override
    public Iterable<Livret> saveAllLivret(Iterable<Livret> livrets) {
        return livretRepository.saveAll(livrets);
    }

    @Override
    public void deleteLivret(long id) {
        livretRepository.deleteById(id);
    }

    @Override
    public void deleteAllLivret() {
        livretRepository.deleteAll();
    }

    @Override
    @Transactional
    public boolean virement(long id1, long id2, double montant) {
        try {
            double montant1 = compteRepository.findById(id1).getSolde();
            if (montant1 < montant && montant>0) {
                return false;
            }
            Compte c1 = compteRepository.findById(id1);
            Compte c2 = compteRepository.findById(id2);
            c1.setSolde(c1.getSolde()-montant);
            c2.setSolde(c2.getSolde()+montant);
            compteRepository.save(c1);
            compteRepository.save(c2);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}