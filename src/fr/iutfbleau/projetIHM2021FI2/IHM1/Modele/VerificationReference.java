package fr.iutfbleau.projetIHM2021FI2.IHM1.Modele;

import fr.iutfbleau.projetIHM2021FI2.MNP.*;
import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Vues.*;

import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;

/**
 * 
 */
public class VerificationReference {

    /**
     * 
     * @param fenetre
     * @param reference
     */
    public VerificationReference(JFrame fenetre, String reference){
        // morceaux de modèle
        PrereservationFactoryNP bookingPointCom = new PrereservationFactoryNP();

        Client c1 =  new ClientNP(1,"Marine","Carpentier");
        Client c2 =  new ClientNP(2, "Aaron","Dumas");
        Client c3 =  new ClientNP(3, "Kimberley", "Leroux");
        Client c4 =  new ClientNP(4, "Florentin", "Giraud");
        Client c5 =  new ClientNP(5, "Martin", "Gillet");

        bookingPointCom.ajoutePrereservation("4751-3708-LRFM", LocalDate.of(2018,1,5),1, TypeChambre.DEUXLS,c1);
        bookingPointCom.ajoutePrereservation("2436-3909-NXLL", LocalDate.of(2018,01,07),1, TypeChambre.UNLS,c1);
        bookingPointCom.ajoutePrereservation("1351-0775-BETZ", LocalDate.of(2018,01,05),2, TypeChambre.DEUXLS,c2);
        bookingPointCom.ajoutePrereservation("3440-0631-NFCU", LocalDate.of(2018,01,06),2, TypeChambre.UNLD,c2);
        bookingPointCom.ajoutePrereservation("1499-2254-DBIU", LocalDate.of(2018,01,04),2, TypeChambre.UNLS,c2);
        bookingPointCom.ajoutePrereservation("5660-8953-YKJO", LocalDate.of(2018,01,06),2, TypeChambre.DEUXLS,c5);

        TypeChambre type2LS = TypeChambre.DEUXLS; //utilisation type énuméré TypeChambre
        Chambre ch1 = new ChambreNP(1, type2LS);

        // une fois que le modèle de PrereservationFactoryNP a du contenu, je peux le caster en l'interface PreservationFactory de l'API correspondante pour que la vue s'en serve.
        
        PrereservationFactory bookingPointComAPISeulement = bookingPointCom;
        System.out.print("Le modèle de Préréservation est prêt.\n");

        try{
            Prereservation prereservation = bookingPointComAPISeulement.getPrereservation(reference);
            System.out.println(reference);
            /*
            TypeChambre type = prereservation.getTypeChambre();
            LocalDate dateDebut = prereservation.getDateDebut();
            int jours = prereservation.getJours();
            System.out.println("Prereservation = "+ prereservation.monPrint());
            System.out.println("Type de Chambre = "+type+ " Date de début = "+dateDebut+" Nombre de jour = "+jours);
            System.out.println("Numéro de chambre : "+ch1.getNumero()+ "Type de chambre : "+ch1.getType());
            */
            new Afficher(fenetre, ch1, prereservation);
        }catch(IllegalStateException e){
            System.out.print("Je n'ai pas trouvé de préreservation avec cette référence.\n");
            new Menu(fenetre, 1);
        }
        
        /*
        //En utilisant une base de données : 
        //il faudrait mettre une autre erreur de base de données
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try{
                Connection connexion = DriverManager.getConnection(
                "jdbc:mariadb://dwarves.iut-fbleau.fr/bernardal",
                    "bernardal", "bernardal");
                /*Connection connexion = DriverManager.getConnection(
                    "jdbc:mariadb://dwarves.iut-fbleau.fr/phpmyadmin",
                        "projetihm", "mhitejorp");
                        */
                   /* try {
                        PreparedStatement commande = connexion.prepareStatement("SELECT * FROM Reservation WHERE reference=?");
                        //Attention ici Reservation doit etre prereservation , mais pour tester j'ai mit reservation car
                        //Le tableau prereservation nexiste pas
                        commande.setString(1, reference);
                        ResultSet result = commande.executeQuery();
                        Prereservation prereservation = (Prereservation) result.getObject(1);
                        connexion.close();
                        new Afficher(fenetre, prereservation);
                    } catch (SQLException g) {
                        System.err.println("Problèmre dans la récupération des données");
                    }
            }catch(SQLException f){
                System.err.println("Problèmre dans la connexion avec la Base de données. ");
                new Menu(fenetre, 1);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Impossible de se connecter à la Base de données.");
            new Menu(fenetre, 1);
        }
        */
        
        
    }  
}
