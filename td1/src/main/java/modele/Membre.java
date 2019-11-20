package modele;

import java.util.ArrayList;
import java.util.Collection;

public class Membre {
    private String login;
    private String motdepasse;
    private String surnom;
    private Collection<Projet> responsable;
    private Collection<Projet> participe;
    private Collection<CompetenceMembre> competenceDeclare;

    // Constructeurs

    public Membre() {
        responsable=new ArrayList<>();
        participe=new ArrayList<>();
        competenceDeclare=new ArrayList<>();
    }

    public Membre(String login, String motdepasse, String surnom) {
        this();
        this.login = login;
        this.motdepasse = motdepasse;
        this.surnom = surnom;
    }

    // Getters / setters

    public String getLogin() {
        return login;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public Collection<Projet> getResponsable() {
        return responsable;
    }

    public void setResponsable(Collection<Projet> responsable) {
        this.responsable = responsable;
    }

    public Collection<Projet> getParticipe() {
        return participe;
    }

    public void setParticipe(Collection<Projet> participe) {
        this.participe = participe;
    }

    public Collection<CompetenceMembre> getCompetenceDeclare() {
        return competenceDeclare;
    }

    public void setCompetenceDeclare(Collection<CompetenceMembre> competenceDeclare) {
        this.competenceDeclare = competenceDeclare;
    }

    public void addResponsable (Projet projet){
        this.responsable.add(projet);
    }

    public void addParticipe (Projet projet) {
        this.participe.add(projet);
    }

    public void addCompetenceMem (CompetenceMembre compMem) {
        this.competenceDeclare.add(compMem);
    }

    public CompetenceMembre findCompetenceMembreByIntitule(String intitule) {
        if (intitule != null) {
            for (CompetenceMembre competenceMembre : this.competenceDeclare) {
                if (competenceMembre.getType().getIntituleC().equals(intitule)) {
                    return competenceMembre;
                }
            }
        }
        return null;
    }

    public boolean deleteCompetenceMembre(String intitule){
        CompetenceMembre competenceMembre = this.findCompetenceMembreByIntitule(intitule);
        if(competenceMembre != null ){
            return this.competenceDeclare.remove(competenceMembre);
        }
        return false;
    }

    public boolean deleteProjet(Projet projet){
        this.responsable.remove(projet);
        this.participe.remove(projet);
        return true;
    }

    public boolean quitProjet(Projet projet){
        return this.participe.remove(projet);
    }

    public boolean participeProjet(Projet projet){
        return this.participe.add(projet);
    }
}
