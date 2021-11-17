package fr.iutfbleau.projetIHM2021FI2.IHM2.Model;

import java.sql.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.MP.ReservationFactoryP;

public class BD {

    public ReservationFactory Connexion(JFrame fenetre){

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try {
                Connection connexion = DriverManager.getConnection(
                    "jdbc:mariadb://dwarves.iut-fbleau.fr/bernardal",
                    "bernardal", "bernardal");

                ReservationFactory grandLivreDOrAPISeulement  = new ReservationFactoryP(connexion);
                return grandLivreDOrAPISeulement;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(fenetre,"La connexion avec la base de données a echoué.");
            }  
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(fenetre,"Impossible de se connecter à la base de données.");
        }
        return null;
    }
}
