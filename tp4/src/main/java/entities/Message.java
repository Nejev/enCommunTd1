package entities;

import javax.persistence.*;

@Entity
public class Message {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_msg;
    private String text;
    @ManyToOne
    private Utilisateur auteur;

    public Integer getId_msg() {
        return id_msg;
    }

    public void setId_msg(Integer id_msg) {
        this.id_msg = id_msg;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Utilisateur getAuteur() {
        return auteur;
    }

    public void setAuteur(Utilisateur auteur) {
        this.auteur = auteur;
    }
}
