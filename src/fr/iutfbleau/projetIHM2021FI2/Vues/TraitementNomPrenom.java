package Vues;
//package fr.iutfbleau.projetIHM2021FI2.Vues;
import javax.swing.*;
import java.awt.event.*;

/**
 * <code>TraitementNomPrenom</code> est un contrôleur.
 * Il gère le bouton lancer du menu
 * 
 * @author Enora GERMOND
 * @version 1.3
 */

public class TraitementNomPrenom implements ActionListener{
    private JFrame fenetre;
    private JTextField prenom;
    private JTextField nom;
    
    /**
     * Constructeur qui permer d'accéder à la fenêtre par la suite
     * @param fenetre the linked window
     */
    public TraitementNomPrenom(JFrame fenetre, JTextField nom, JTextField prenom) {
        this.fenetre = fenetre;
        this.prenom = prenom;
        this.nom = nom;
    }

    /**
     * Lors du clic sur le bouton
     * @param e the action data
     */
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println("Nom : " + this.nom.getText() + " Prénom : " + this.prenom.getText());
        new VerificationNomPrenom(this.nom.getText(), this.prenom.getText());
    }
    
    
}