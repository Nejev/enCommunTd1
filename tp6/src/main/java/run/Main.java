package run;

import service.BanqueService;
import modele.Client;
import modele.Compte;
import modele.Livret;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    //Service spring
    private static BanqueService service;

    public static void main(String[] args) throws ParseException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
        // récupération de la couche service
        service = (BanqueService) ctx.getBean("banqueService");
        //Setup + opérations et visu de la bdd
        clean();
        setup();
        viewClients();
        viewComptes();
        viewLivrets();
        viewClientsComptes();
        virement();
        viewComptes();
    }

    //virement entre 2 clients
    private static void virement() {

        service.virement(1, 2, 600.0);
    }

    // affichage table client
    private static void viewClients() {
        System.out.println("CLIENTS:");
        for (Client client : service.getAllClient()) {
            System.out.println(client.getId()+"_"+client.getNom());
        }
    }
    // affichage table Livret
    private static void viewLivrets() {
        System.out.println("LIVRETS:");
        for (Livret livret : service.getAllLivret()) {
            System.out.println(livret.getId()+"_"+livret.getTitulaire());
        }
    }
    // affichage des comptes  
    private static void viewComptes() {
        System.out.println("COMPTES:");
        for (Compte compte : service.getAllCompte()) {
            System.out.println(compte.getId()+"_"+compte.getTitulaire());
        }
    }
    //affichage de tous les comptes de tous les clients
    private static void viewClientsComptes() {
        System.out.println("CLIENT_ET_COMPTES");
        for (Client client : service.getAllClient()) {
            for (Compte compte : service.getAllCompteById_client(client.getId())) {
                System.out.println(client.getNom()+"_"+compte.getSolde());
            }
        }
    }
    // remplissage des tables
    public static void setup() throws ParseException {
        Client c1 = new Client(1, "paf", "paf", "Orleans");
        Client c2 = new Client(2, "pouf", "pouf", "Paris");
        Client c3 = new Client(3, "dune", "dune", "Marseille"); 
        c1.addCompte(new Compte(1,c1,56546.0,new SimpleDateFormat("dd/MM/yy").parse( "10/12/2000")));
        c1.addCompte(new Compte(2,c1,6546.0,new SimpleDateFormat("dd/MM/yy").parse( "10/12/2001")));
        c1.addCompte(new Livret(3,c1, 656.0,new SimpleDateFormat("dd/MM/yy").parse( "10/12/2002"),0.05));
        c2.addCompte(new Compte(4,c2, 595.0,new SimpleDateFormat("dd/MM/yy").parse( "01/12/2003")));
        // persistance des Clients avec leurs comptes/livrets  
        service.saveClient(new Client[]{c1,c2,c3});
    }
    // supprime toutes les données
    public static void clean() {
        for (Client Client : service.getAllClient()) {
            service.deleteClient(Client.getId());
        }
    }
}
