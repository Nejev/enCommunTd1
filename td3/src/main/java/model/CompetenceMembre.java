package model;

public class CompetenceMembre {
    private int niveau;
    private String commentaire;
    private Competence type;
    private Membre declarePar;

    public CompetenceMembre() {

    }

    public CompetenceMembre(int niv, String com) {
        this.niveau = niv;
        this.commentaire = com;
    }

    public CompetenceMembre(int niv, String com, Competence type, Membre membre) {
        this.niveau = niv;
        this.commentaire = com;
        this.type = type;
        this.declarePar = membre;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public Competence getType() {
        return type;
    }

    public void setType(Competence type) {
        this.type = type;
    }

    public Membre getDeclarePar() {
        return declarePar;
    }

    public void setDeclarePar(Membre declarePar) {
        this.declarePar = declarePar;
    }
}
