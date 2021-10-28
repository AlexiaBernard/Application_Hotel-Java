package fr.iutfbleau.projetIHM2021FI2.IHM2.Model;

import java.sql.*;
import javax.swing.*;

public class BD {

    private JFrame fenetre;

    public BD(JFrame fenetre){
        this.fenetre = fenetre;
    }

    public Connection Connexion(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connexion = DriverManager.getConnection(
            "jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm",
            "projetihm", "mhitejorp");
            return connexion;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.fenetre,"Problème de connexion avec la Base de donées.");
            return null;
        }
    }

}
