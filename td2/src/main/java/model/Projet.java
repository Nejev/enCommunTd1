package model;

import java.util.ArrayList;
import java.util.Collection;

public class Projet {
    private String intituleP;
    private String descriptionP;
    private Membre dirigePar;
    private Collection<Membre> contributionDe;
    private Collection<Competence> necessite;

    // Constructeurs
    public Projet() {
        this.contributionDe = new ArrayList<>();
        this.necessite = new ArrayList<>();
    }

    public Projet(String intituleP, String descriptionP) {
        this();
        this.intituleP = intituleP;
        this.descriptionP = descriptionP;
    }

    // Getters et setters

    public String getIntituleP() {
        return intituleP;
    }

    public void setIntituleP(String intituleP) {
        this.intituleP = intituleP;
    }

    public String getDescriptionP() {
        return descriptionP;
    }

    public void setDescriptionP(String descriptionP) {
        this.descriptionP = descriptionP;
    }

    public Membre getDirigePar() {
        return dirigePar;
    }

    public void setDirigePar(Membre dirigePar) {
        this.dirigePar = dirigePar;
    }

    public Collection<Membre> getContributionDe() {
        return contributionDe;
    }

    public void setContributionDe(Collection<Membre> contributionDe) {
        this.contributionDe = contributionDe;
    }

    public Collection<Competence> getNecessite() {
        return necessite;
    }

    public void setNecessite(Collection<Competence> necessite) {
        this.necessite = necessite;
    }

    public void addParticipe(Membre user) {
        this.contributionDe.add(user);
    }

    public void addCompetence(Competence comp) {
        this.necessite.add(comp);
    }

    public boolean deleteMembre(Membre membre) {
        return this.contributionDe.remove(membre);
    }

    public boolean addContribue(Membre membre) {
        return this.contributionDe.add(membre);
    }
}
