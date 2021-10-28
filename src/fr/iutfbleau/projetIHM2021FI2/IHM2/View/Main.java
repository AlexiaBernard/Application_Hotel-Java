package fr.iutfbleau.projetIHM2021FI2.IHM2.View;

import java.sql.*;

import javax.swing.JFrame;

import fr.iutfbleau.projetIHM2021FI2.IHM2.Model.*;

public class Main {
    
    public static void main(String[] args) {

        JFrame fenetre = new JFrame("IHM2");
        
        BD baseDeDonnées = new BD(fenetre);
        Connection connexion = baseDeDonnées.Connexion();
        if (connexion != null)
            System.out.println("Connexion OK!");

    }

}
