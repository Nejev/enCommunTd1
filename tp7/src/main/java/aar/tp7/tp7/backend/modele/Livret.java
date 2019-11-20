package aar.tp7.tp7.backend.modele;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Livret extends Compte{

    private double tauxInteret;

    public Livret() {
    }

    public Livret(Client titulaire, double solde ,Date dateOuverture, double tauxInteret) {
        this.setTitulaire(titulaire);
        this.setSolde(solde);
        this.setDateOuverture(dateOuverture);
        this.tauxInteret=tauxInteret;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }
}