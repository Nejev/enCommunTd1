package aar.tp7.tp7;

import aar.tp7.tp7.backend.modele.Client;
import aar.tp7.tp7.backend.modele.Compte;
import aar.tp7.tp7.backend.modele.Livret;
//import aar.tp7.tp7.backend.service.BanqueService;
import aar.tp7.tp7.backend.service.BanqueService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class Tp7Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp7Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(BanqueService service) {
        return (args) ->{
            System.out.println("Start");

            //Supression ancienne base
            System.out.println("Suppression des anciennes données");
            service.deleteAllClient();
            //service.deleteAllCompte();

            //Ajout client
            System.out.println("Ajout des clients");
            ArrayList<Client> listAdd =new ArrayList<>();
            listAdd.add(new Client("LANES", "truc", "adresse"));
            listAdd.add(new Client("NEGEV", "negev", "adresse"));
            service.saveAllClient(listAdd);

            //Update de lanes
            System.out.println("Update de lanes");
            Client lanes = service.findClient("LANES").iterator().next();
            lanes.setPrenom("lanes");
            service.saveClient(lanes);

            //Apercu de la table
            System.out.println("Apercu de la table");
            Iterable<Client> list = service.findAllClient();
            System.out.println("Client dans la base: "+list.toString());

            //Ajout de compte à lanes
            System.out.println("Ajout d'un compte à lanes");
            lanes = service.findClient("LANES").iterator().next();
            Compte c1 = new Compte(lanes,100.0,new Date(System.currentTimeMillis()));
            Livret l1 = new Livret(lanes,1000.0,new Date(System.currentTimeMillis()),3.0);
            lanes.addCompte(c1);
            lanes.addCompte(l1);
            service.saveClient(lanes);

            //Ajout de compte à negev
            System.out.println("Ajout d'un compte à negev");
            Client negev = service.findClient("NEGEV").iterator().next();
            Compte c2 = new Compte(negev,500.0,new Date(System.currentTimeMillis()));
            Livret l2 = new Livret(negev,0.0,new Date(System.currentTimeMillis()),3.0);
            negev.addCompte(c2);
            negev.addCompte(l2);
            service.saveClient(negev);

            //Virement de lanes de son compte courant vers son livret
            ArrayList<Compte> comptesLanes = new ArrayList<>(service.findClient("LANES").iterator().next().getComptes());
            if(service.virement(comptesLanes.get(0).getId(),comptesLanes.get(1).getId(),50.0)){
                System.out.println("Virement réussi");
            }else{
                System.out.println("Echec du virement");
            }

            //Apercu de la table
            System.out.println("Apercu de la table");
            Iterable<Client> list2 = service.findAllClient();
            System.out.println("Client dans la base: "+list2.toString());

            System.out.println("End");
        };
    }
}