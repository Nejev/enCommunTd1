package modele;

import javax.persistence.Entity;

@Entity
public class Livret extends Compte {
    private double tauxInteret;

    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }
}
