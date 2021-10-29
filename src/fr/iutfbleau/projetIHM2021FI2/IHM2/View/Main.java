package fr.iutfbleau.projetIHM2021FI2.IHM2.View;

import java.sql.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.IHM2.Model.*;

public class Main {
    
    public static void main(String[] args) {

        JFrame fenetre = new JFrame("IHM2");

        fenetre.setSize(500, 500);
	    fenetre.setLocation(100, 100);
	    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /*BD baseDeDonnées = new BD(fenetre);
        boolean verif = baseDeDonnées.Connexion();
        if (verif == true){
            JOptionPane.showMessageDialog(fenetre,"La Base de données est prête.");
            Bureau bureau = new Bureau(fenetre, baseDeDonnées);
            bureau.run();
        }   */
        Bureau bureau = new Bureau(fenetre, null);
        bureau.run();
    }
}
