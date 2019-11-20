package run;

import modele.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banquePU");
        EntityManager em = emf.createEntityManager();

        Client client = new Client();
        client.setId(1);
        client.setNom("didier");
        client.setPrenom("pascal");
        client.setAdresse("93eutréparis");
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
    }


}
