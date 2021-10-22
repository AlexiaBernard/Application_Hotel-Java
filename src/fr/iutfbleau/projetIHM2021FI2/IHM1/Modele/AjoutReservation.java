package fr.iutfbleau.projetIHM2021FI2.IHM1.Modele;

import java.sql.*;

import javax.swing.JFrame;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Vues.*;

public class AjoutReservation {
    
    public AjoutReservation(JFrame fenetre, Chambre chambre, Prereservation prereservation){
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
                try {
                    PreparedStatement commande = connexion.prepareStatement("INSERT INTO Reservation (prereservation, chambre) VALUES (?,?)");
                    commande.setString(1, prereservation.getReference());
                    commande.setInt(2,chambre.getNumero());
                    commande.executeUpdate();
                    System.out.println("Réservation validée");
                    connexion.close();
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
    }
}
