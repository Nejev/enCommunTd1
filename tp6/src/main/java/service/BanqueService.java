package service;

import modele.Client;
import modele.Compte;
import modele.Livret;

public interface BanqueService {

    Client getClient(long id_client);

    Client[] getAllClient();

    Livret[] getAllLivret();

    Compte[] getAllCompte();

    Compte[] getAllCompteById_client(long id_client);

    boolean virement(int id_client_1, int id_client_2, double montant);

    boolean saveClient(Client[] clients);

    boolean deleteClient(long id_client);
}
