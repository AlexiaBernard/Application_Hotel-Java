package fr.iutfbleau.projetIHM2021FI2.IHM1.View;

import javax.swing.*;
import java.sql.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.MP.*;

public class MainMP {

    public static void main(String[] args) {

        JFrame fenetre = new JFrame("Menu");

        fenetre.setSize(500, 500);
        fenetre.setLocation(100, 100);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try {
                Connection connexion = DriverManager.getConnection(
                    "jdbc:mariadb://dwarves.iut-fbleau.fr/bernardal",
                    "bernardal", "bernardal");

                PrereservationFactoryP bookingPointCom = new PrereservationFactoryP(connexion);
        
                PrereservationFactory bookingPointComAPISeulement = bookingPointCom;
                JOptionPane.showMessageDialog(fenetre,"Les modèles de préréservation et de réservation sont prêts.");

                ReservationFactory grandLivreDOrAPISeulement  = new ReservationFactoryP(connexion);

                JPanel centre = new JPanel();

                //Lancement du menu
                Menu menu = new Menu(bookingPointComAPISeulement, grandLivreDOrAPISeulement,fenetre, centre);
                menu.run();
            } catch (SQLException e) {
                System.err.println("La connexion avec la base de données a echoué.");
            }  
        } catch (ClassNotFoundException e) {
            System.err.println("Impossible de se connecter à la base de données.");
        }
    }    
}
