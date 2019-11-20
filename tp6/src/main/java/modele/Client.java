package modele;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "Client")
public class Client {
    @Id
    private long id;
    private String nom;
    private String prenom;
    private String adresse;
    @OneToMany
    private Collection<Compte> comptes;

    public Client() {
        comptes = new ArrayList<>();
    }

    public Client(long id_client, String nom, String prenom, String adresse, Collection<Compte> comptes) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.comptes = comptes;
    }

    public Client(int i, String martin, String paul, String orléans) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Collection<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(Collection<Compte> comptes) {
        this.comptes = comptes;
    }

    public void addCompte(Compte compte) {
    }
}

