package service;

import modele.Client;
import modele.Compte;
import modele.Livret;

public class BanqueServiceImpl implements BanqueService{

    @Override
    public Client getClient(long id_client)
    {
        return null;
    }

    @Override
    public Client[] getAllClient()
    {
        return null;
    }

    @Override
    public Livret[] getAllLivret()
    {
        return null;
    }

    @Override
    public Compte[] getAllCompte() {

        return null;
    }

    @Override
    public Compte[] getAllCompteById_client(long id_client) {

        return null;
    }

    @Override
    public boolean saveClient(Client[] clients) {
        return false;
    }

    @Override
    public boolean deleteClient(long id_client) {
        return false;
    }

    @Override
    public boolean virement(int id_client_1, int id_client_2, double montant) {
        return false;
    }
}