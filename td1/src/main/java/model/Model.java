package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Model {
    private ArrayList<Membre> membreList;
    private ArrayList<Projet> projetList;
    private ArrayList<Competence> competenceList;

    public Model(){
        //init liste des membres
        this.membreList = new ArrayList<>();
        this.membreList.add(new Membre("user1", "one", "user 1"));
        this.membreList.add(new Membre("user2", "two", "user 2"));
        this.membreList.add(new Membre("user3", "three", "user 3"));
        //init liste projets
        this.projetList = new ArrayList<>();
        this.projetList.add(new Projet("projet1", "Projet 1"));
        this.projetList.add(new Projet("projet2", "Projet 2"));
        this.projetList.add(new Projet("projet3", "Projet 3"));
        this.projetList.add(new Projet("projet4", "Projet 4"));
        this.projetList.add(new Projet("projet5", "Projet 5"));
        this.projetList.add(new Projet("projet6", "Projet 6"));
        this.projetList.add(new Projet("projet7", "Projet 7"));
        this.projetList.add(new Projet("projet8", "Projet 8"));
        this.projetList.add(new Projet("projet9", "Projet 9"));
        //init liste competences
        this.competenceList = new ArrayList<>();
        this.addCompetence("comp1", "compétence 1");
        this.addCompetence("comp2", "compétence 2");
        this.addCompetence("comp3", "compétence 3");
        this.addCompetence("comp4", "compétence 4");
        this.addCompetence("comp5", "compétence 5");
        //relations
        //projet dirigé par un membre
        this.setMembreToDirigeProjet(this.findMembreByLogin("user1"),this.findProjetByIntitule("projet1"));
        this.setMembreToDirigeProjet(this.findMembreByLogin("user2"),this.findProjetByIntitule("projet2"));
        this.setMembreToDirigeProjet(this.findMembreByLogin("user3"),this.findProjetByIntitule("projet3"));
        this.setMembreToDirigeProjet(this.findMembreByLogin("user3"),this.findProjetByIntitule("projet4"));
        this.setMembreToDirigeProjet(this.findMembreByLogin("user3"),this.findProjetByIntitule("projet5"));
        this.setMembreToDirigeProjet(this.findMembreByLogin("user3"),this.findProjetByIntitule("projet6"));
        this.setMembreToDirigeProjet(this.findMembreByLogin("user3"),this.findProjetByIntitule("projet7"));
        this.setMembreToDirigeProjet(this.findMembreByLogin("user3"),this.findProjetByIntitule("projet8"));
        this.setMembreToDirigeProjet(this.findMembreByLogin("user3"),this.findProjetByIntitule("projet9"));
        //membre participe a un projet
        this.setMembreToParticipeProjet(this.findMembreByLogin("user1"),this.findProjetByIntitule("projet2"));
        this.setMembreToParticipeProjet(this.findMembreByLogin("user1"),this.findProjetByIntitule("projet3"));
        this.setMembreToParticipeProjet(this.findMembreByLogin("user2"),this.findProjetByIntitule("projet6"));
        this.setMembreToParticipeProjet(this.findMembreByLogin("user2"),this.findProjetByIntitule("projet5"));
        this.setMembreToParticipeProjet(this.findMembreByLogin("user3"),this.findProjetByIntitule("projet1"));
        //competences requises
        this.setCompetenceToProjet(this.findCompetenceByIntitule("comp1"),this.findProjetByIntitule("projet1"));
        this.setCompetenceToProjet(this.findCompetenceByIntitule("comp1"),this.findProjetByIntitule("projet2"));
        this.setCompetenceToProjet(this.findCompetenceByIntitule("comp1"),this.findProjetByIntitule("projet3"));
        this.setCompetenceToProjet(this.findCompetenceByIntitule("comp1"),this.findProjetByIntitule("projet4"));
        //Attribution des competences
        this.setCompetenceToMembre(this.findCompetenceByIntitule("comp1"),this.findMembreByLogin("user1"),3,"Ceci est un commentaire");
        this.setCompetenceToMembre(this.findCompetenceByIntitule("comp1"),this.findMembreByLogin("user2"),3,"Ceci est un commentaire");
        this.setCompetenceToMembre(this.findCompetenceByIntitule("comp1"),this.findMembreByLogin("user3"),3,"Ceci est un commentaire");
    }

    public ArrayList<Competence> getCompetenceList() {
        return competenceList;
    }

    public ArrayList<Membre> getMembreList() {
        return membreList;
    }

    public void setMembreList(ArrayList<Membre> membreList) {
        this.membreList = membreList;
    }

    public ArrayList<Projet> getProjetList() {
        return projetList;
    }

    public void setProjetList(ArrayList<Projet> projetList) {
        this.projetList = projetList;
    }

    public void setCompetenceList(ArrayList<Competence> competenceList) {
        this.competenceList = competenceList;
    }

    public Membre findMembreByLogin(String login) {
        if (login != null) {
            for (Membre membre : this.membreList) {
                if (membre.getLogin().equals(login)) {
                    return membre;
                }
            }
        }
        return null;
    }

    public Projet findProjetByIntitule(String intitule) {
        if (intitule != null) {
            for (Projet projet : this.projetList) {
                if (projet.getIntituleP().equals(intitule)) {
                    return projet;
                }
            }
        }
        return null;
    }

    public Competence findCompetenceByIntitule(String intitule) {
        if (intitule != null) {
            for (Competence competence : this.competenceList) {
                if (competence.getIntituleC().equals(intitule)) {
                    return competence;
                }
            }
        }
        return null;
    }

    public void setMembreToDirigeProjet(Membre user, Projet projet) {
        projet.setDirigePar(user);
        user.addResponsable(projet);
    }

    public void setMembreToParticipeProjet(Membre user, Projet projet) {
        projet.addParticipe(user);
        user.addParticipe(projet);
    }

    public void setCompetenceToProjet(Competence comp, Projet projet) {
        projet.addCompetence(comp);
        comp.addProjet(projet);
    }

    public void setCompetenceToMembre(Competence comp, Membre membre, int niv, String com) {
        CompetenceMembre competenceMembrePresente=membre.findCompetenceMembreByIntitule(comp.getIntituleC());
        if(competenceMembrePresente==null) {
            CompetenceMembre temp = new CompetenceMembre(niv, com, comp, membre);
            comp.addCompetenceMembre(temp);
            membre.addCompetenceMem(temp);
        }else{
            competenceMembrePresente.setCommentaire(com);
            competenceMembrePresente.setNiveau(niv);
        }
    }

    public boolean addCompetence(String intitule,String description){
        Competence competence = this.findCompetenceByIntitule(intitule);
        if(competence==null){
            this.competenceList.add(new Competence(intitule,description));
        }else{
            competence.setDescriptionC(description);
            competence.setIntituleC(intitule);
        }
        return true;
    }

    public boolean deleteCompetenceMembre(Membre membre, String intitule){
        Competence competence = this.findCompetenceByIntitule(intitule);
        if(competence != null){
            return membre.deleteCompetenceMembre(intitule) && competence.deleteCompetenceMembre(membre);
        }
        return false;
    }

    private boolean membreHaveCompForProjet(Membre membre, Projet projet) {
        for (CompetenceMembre compMem : membre.getCompetenceDeclare()) {
            if(projet.getNecessite().contains(compMem.getType())){
                return true;
            }
        }
        return false;
    }

    public HashMap<String,ArrayList<Projet>> dispatchProject(Membre membre) {
        HashMap<String, ArrayList<Projet>> map = new HashMap<>();
        map.put("responsable", new ArrayList<>());
        map.put("participe", new ArrayList<>());
        map.put("competence", new ArrayList<>());
        map.put("autre", new ArrayList<>());
        for (Projet projet : this.projetList) {
            if(membre.getResponsable().contains(projet)){
                //le membre dirige le projet
                map.get("responsable").add(projet);
            } else if (membre.getParticipe().contains(projet)) {
                //le membre contribue au projet
                map.get("participe").add(projet);
            } else if (this.membreHaveCompForProjet(membre,projet)) {
                //le membre possède les compétences pour ce projet
                map.get("competence").add(projet);
            } else {
                map.get("autre").add(projet);
            }
        }
        return map;
    }
}
