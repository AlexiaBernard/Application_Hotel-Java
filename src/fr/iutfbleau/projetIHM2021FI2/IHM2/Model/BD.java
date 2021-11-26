package fr.iutfbleau.projetIHM2021FI2.IHM2.Model;

import java.sql.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.MP.ReservationFactoryP;

/**
 * <code>BD</code> est un modèle.
 * Elle permet de se connecter à la base de données et à envoyer un message d'erreur si la connexion échoue.
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class BD {

    /**
     * Connexion à la base de données
     * 
     * @param fenetre la fenetre
     * @return null
     */
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
                System.exit(1);
            }  
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(fenetre,"Impossible de se connecter à la base de données.");
            System.exit(1);
        }
        return null;
    }
}
