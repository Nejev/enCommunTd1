package services;

import model.Competence;
import model.CompetenceMembre;
import model.Membre;
import model.Projet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class ModelService {
    private ArrayList<Membre> membreList;
    private ArrayList<Projet> projetList;
    private ArrayList<Competence> competenceList;

    public ModelService() {
        //init liste des membres
        this.membreList = new ArrayList<>();
        this.createMembre("user1", "one", "user 1");
        this.createMembre("user2", "two", "user 2");
        this.createMembre("user3", "three", "user 3");
        //init liste projets
        this.projetList = new ArrayList<>();
        this.createProjet(this.findMembreByLogin("user1"), "projet1", "Projet 1");
        this.createProjet(this.findMembreByLogin("user3"), "projet2", "Projet 2");
        this.createProjet(this.findMembreByLogin("user3"), "projet3", "Projet 3");
        this.createProjet(this.findMembreByLogin("user3"), "projet4", "Projet 4");
        this.createProjet(this.findMembreByLogin("user3"), "projet5", "Projet 5");
        this.createProjet(this.findMembreByLogin("user3"), "projet6", "Projet 6");
        this.createProjet(this.findMembreByLogin("user3"), "projet7", "Projet 7");
        this.createProjet(this.findMembreByLogin("user3"), "projet8", "Projet 8");
        this.createProjet(this.findMembreByLogin("user3"), "projet9", "Projet 9");
        //init liste competences
        this.competenceList = new ArrayList<>();
        this.addCompetence("comp1", "compétence 1");
        this.addCompetence("comp2", "compétence 2");
        this.addCompetence("comp3", "compétence 3");
        this.addCompetence("comp4", "compétence 4");
        this.addCompetence("comp5", "compétence 5");
        //relations
        //membre participe a un projet
        this.setMembreToParticipeProjet(this.findMembreByLogin("user1"), this.findProjetByIntitule("projet2"));
        this.setMembreToParticipeProjet(this.findMembreByLogin("user1"), this.findProjetByIntitule("projet3"));
        this.setMembreToParticipeProjet(this.findMembreByLogin("user2"), this.findProjetByIntitule("projet6"));
        this.setMembreToParticipeProjet(this.findMembreByLogin("user2"), this.findProjetByIntitule("projet5"));
        this.setMembreToParticipeProjet(this.findMembreByLogin("user3"), this.findProjetByIntitule("projet1"));
        //competences requises
        this.setCompetenceToProjet(this.findCompetenceByIntitule("comp1"), this.findProjetByIntitule("projet1"));
        this.setCompetenceToProjet(this.findCompetenceByIntitule("comp1"), this.findProjetByIntitule("projet2"));
        this.setCompetenceToProjet(this.findCompetenceByIntitule("comp1"), this.findProjetByIntitule("projet3"));
        this.setCompetenceToProjet(this.findCompetenceByIntitule("comp1"), this.findProjetByIntitule("projet4"));
        //Attribution des competences
        this.setCompetenceToMembre(this.findCompetenceByIntitule("comp1"), this.findMembreByLogin("user1"), 3, "Ceci est un commentaire");
        this.setCompetenceToMembre(this.findCompetenceByIntitule("comp1"), this.findMembreByLogin("user2"), 3, "Ceci est un commentaire");
        this.setCompetenceToMembre(this.findCompetenceByIntitule("comp1"), this.findMembreByLogin("user3"), 3, "Ceci est un commentaire");
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

    public boolean createMembre(String login, String mdp, String surnom) {
        if (login != "" && mdp != "" && this.findMembreByLogin(login) == null) {
            return this.membreList.add(new Membre(login, mdp, surnom));
        }
        return false;
    }

    public void setCompetenceToProjet(Competence comp, Projet projet) {
        projet.addCompetence(comp);
        comp.addProjet(projet);
    }

    public void setCompetenceToMembre(Competence comp, Membre membre, int niv, String com) {
        CompetenceMembre competenceMembrePresente = membre.findCompetenceMembreByIntitule(comp.getIntituleC());
        if (competenceMembrePresente == null) {
            CompetenceMembre temp = new CompetenceMembre(niv, com, comp, membre);
            comp.addCompetenceMembre(temp);
            membre.addCompetenceMem(temp);
        } else {
            competenceMembrePresente.setCommentaire(com);
            competenceMembrePresente.setNiveau(niv);
        }
    }

    public boolean addCompetence(String intitule, String description) {
        Competence competence = this.findCompetenceByIntitule(intitule);
        if (competence == null) {
            this.competenceList.add(new Competence(intitule, description));
        } else {
            competence.setDescriptionC(description);
            competence.setIntituleC(intitule);
        }
        return true;
    }

    public boolean deleteCompetenceMembre(Membre membre, String intitule) {
        Competence competence = this.findCompetenceByIntitule(intitule);
        if (competence != null) {
            return membre.deleteCompetenceMembre(intitule) && competence.deleteCompetenceMembre(membre);
        }
        return false;
    }

    public boolean createProjet(Membre membre, String intitule, String description) {
        Projet projet = findProjetByIntitule(intitule);
        if (projet == null) {
            projet = new Projet(intitule, description);
            this.setMembreToDirigeProjet(membre, projet);
            this.projetList.add(projet);
        } else {
            if (projet.getDirigePar().equals(membre)) {
                projet.setIntituleP(intitule);
                projet.setDescriptionP(description);
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean deleteProjet(String intitule) {
        Projet projet = this.findProjetByIntitule(intitule);
        if (projet != null) {
            for (Membre membre : this.membreList) {
                membre.deleteProjet(projet);
            }
            for (Competence competence : this.competenceList) {
                competence.deleteProjet(projet);
            }
            this.projetList.remove(projet);
            return true;
        }
        return false;
    }

    public boolean quitProjet(Membre membre, String intitule) {
        Projet projet = this.findProjetByIntitule(intitule);
        if (projet != null) {
            projet.deleteMembre(membre);
            membre.quitProjet(projet);
            return true;
        }
        return false;
    }

    public boolean participeProjet(Membre membre, String intitule) {
        Projet projet = this.findProjetByIntitule(intitule);
        if (projet != null && membreHaveCompForProjet(membre, projet)) {
            projet.addContribue(membre);
            membre.participeProjet(projet);
            return true;
        }
        return false;
    }

    private boolean membreHaveCompForProjet(Membre membre, Projet projet) {
        for (CompetenceMembre compMem : membre.getCompetenceDeclare()) {
            if (projet.getNecessite().contains(compMem.getType())) {
                return true;
            }
        }
        return false;
    }

    public HashMap<String, ArrayList<Projet>> dispatchProject(Membre membre) {
        HashMap<String, ArrayList<Projet>> map = new HashMap<>();
        map.put("responsable", new ArrayList<>());
        map.put("participe", new ArrayList<>());
        map.put("competence", new ArrayList<>());
        map.put("autre", new ArrayList<>());
        for (Projet projet : this.projetList) {
            if (membre.getResponsable().contains(projet)) {
                //le membre dirige le projet
                map.get("responsable").add(projet);
            } else if (membre.getParticipe().contains(projet)) {
                //le membre contribue au projet
                map.get("participe").add(projet);
            } else if (this.membreHaveCompForProjet(membre, projet)) {
                //le membre possède les compétences pour ce projet
                map.get("competence").add(projet);
            } else {
                map.get("autre").add(projet);
            }
        }
        return map;
    }
}
