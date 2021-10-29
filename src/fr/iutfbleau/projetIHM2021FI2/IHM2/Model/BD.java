package fr.iutfbleau.projetIHM2021FI2.IHM2.Model;

import java.sql.*;
import javax.swing.*;

public class BD {

    private JFrame fenetre;
    private Connection connexion;

    public BD(JFrame fenetre){
        this.fenetre = fenetre;
        this.connexion = null;
    }

    public boolean Connexion(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            this.connexion = DriverManager.getConnection(
            "jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm",
            "projetihm", "mhitejorp");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.fenetre,"Problème de connexion avec la Base de donées.");
            this.fenetre.dispose();
            return false;
        }
    }

    public Connection getConnexion(){
        return this.connexion;
    }

    public Boolean Close(){
        try {
            this.connexion.close();
            JOptionPane.showMessageDialog(this.fenetre,"La Base de données n'est plus disponible.");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.fenetre,"Problème de fermeture de la Base de données");
            //...
            return false;
        }        
    }

}
