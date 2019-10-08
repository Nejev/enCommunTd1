package services;

import dto.MessageDto;
import entities.Message;
import entities.Utilisateur;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class MurService {
    @PersistenceContext
    EntityManager em;

    public Utilisateur findUtilisateurByLogin(String login) {
        return em.find(Utilisateur.class,login);
    }

    public Utilisateur findUtilisateurByLoginPassword(String login, String password) {
        Query q=em.createQuery("from Utilisateur u where u.login=:l and u.password=:p");
        q.setParameter("l",login);
        q.setParameter("p",password);
        try {
            return (Utilisateur) q.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public Collection<Utilisateur> findAllUtilisateur() {
        Query q=em.createQuery("from Utilisateur u");
        return q.getResultList();
    }

    public Utilisateur createUtilisateur() {
        // TODO !!!
        return null;
    }

    @Transactional
    public void createMessage(String login, String message) {
        Utilisateur u = em.find(Utilisateur.class,login);
        Message msg = new Message();
        msg.setAuteur(u);
        msg.setText(message);
        em.persist(msg);

    }

    public Collection<MessageDto> findAllMessage() {
        Collection<MessageDto> messages=new ArrayList<>();
        /*MessageDto msg=new MessageDto();
        msg.setPseudo("Luc");
        Collection<String> msgs=new ArrayList<>();
        msgs.add("bonjour");
        msgs.add("au revoir");
        msg.setMessages(msgs);
        messages.add(msg);

        msg=new MessageDto();
        msg.setPseudo("Jeannot");
        msgs=new ArrayList<>();
        msgs.add("Je suis un lapin");
        msgs.add("Je mange des carottes");
        msg.setMessages(msgs);
        messages.add(msg);*/

        Query q=em.createQuery("From Utilisateur u Order By u.pseudo");
        Collection <Utilisateur> utils = q.getResultList();

        for (Utilisateur u : utils) {
            MessageDto msg = new MessageDto();
            msg.setPseudo(u.getPseudo());

            Collection<String> msgs = new ArrayList<>();
            for (Message m : u.getMessages()){
                msgs.add(m.getText());
            }
            msg.setMessages(msgs);
            messages.add(msg);
        }

        return messages;
    }

    public Collection<MessageDto> findMessageByWord(String mot) {
        Collection<MessageDto> messages=new ArrayList<>();
        /*MessageDto msg=new MessageDto();
        msg.setPseudo("Luc");
        Collection<String> msgs=new ArrayList<>();
        msgs.add("bonjour");
        msgs.add("au revoir");
        msg.setMessages(msgs);
        messages.add(msg);

        msg=new MessageDto();
        msg.setPseudo("Jeannot");
        msgs=new ArrayList<>();
        msgs.add("Je suis un lapin");
        msgs.add("Je mange des carottes");
        msg.setMessages(msgs);
        messages.add(msg);*/

        Query q=em.createQuery("SELECT u From Utilisateur u JOIN u.messages m WHERE m.text like :mot Order By u.pseudo");
        q.setParameter("mot","%"+mot+"%");
        Collection <Utilisateur> utils = q.getResultList();

        for (Utilisateur u : utils) {
            MessageDto msg = new MessageDto();
            msg.setPseudo(u.getPseudo());

            Collection<String> msgs = new ArrayList<>();
            for (Message m : u.getMessages()){
                msgs.add(m.getText());
            }
            msg.setMessages(msgs);
            messages.add(msg);
        }

        return messages;
    }


}
