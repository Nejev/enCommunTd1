package modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Livret")
public class Livret extends Compte{
    @Id
    private double tauxInteret;

    public Livret() {
    }

    public Livret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public Livret(int i, Client c2, double v, Date parse, double v1) {
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }
}
