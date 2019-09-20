package model;

import java.util.ArrayList;
import java.util.Collection;

public class Competence {

    private String intituleC;
    private String descriptionC;
    private Collection<Projet> requisePour;
    private Collection<CompetenceMembre> correspond;

    // Constructeurs

    public Competence() {
        this.requisePour=new ArrayList<>();
        this.correspond=new ArrayList<>();
    }

    public Competence(String intituleC, String descriptionC) {
        this();
        this.intituleC = intituleC;
        this.descriptionC = descriptionC;
    }

    // Getters et setters

    public String getIntituleC() {
        return intituleC;
    }

    public void setIntituleC(String intituleC) {
        this.intituleC = intituleC;
    }

    public String getDescriptionC() {
        return descriptionC;
    }

    public void setDescriptionC(String descriptionC) {
        this.descriptionC = descriptionC;
    }

    public Collection<Projet> getRequisePour() {
        return requisePour;
    }

    public void setRequisePour(Collection<Projet> requisePour) {
        this.requisePour = requisePour;
    }

    public Collection<CompetenceMembre> getCorrespond() {
        return correspond;
    }

    public void setCorrespond(Collection<CompetenceMembre> correspond) {
        this.correspond = correspond;
    }

    public void addProjet(Projet projet) {
        this.requisePour.add(projet);
    }

    public void addCompetenceMembre(CompetenceMembre compMem) {
        this.correspond.add(compMem);
    }

    public CompetenceMembre findCompetenceMembreByMembre(Membre membre) {
        if (membre != null) {
            for (CompetenceMembre competenceMembre : this.correspond) {
                if (competenceMembre.getDeclarePar().equals(membre)) {
                    return competenceMembre;
                }
            }
        }
        return null;
    }

    public boolean deleteCompetenceMembre(Membre membre){
        CompetenceMembre competenceMembre = this.findCompetenceMembreByMembre(membre);
        if(competenceMembre != null ){
            return this.correspond.remove(competenceMembre);
        }
        return false;
    }

    public boolean deleteProjet(Projet projet){
        return this.requisePour.remove(projet);
    }
}
