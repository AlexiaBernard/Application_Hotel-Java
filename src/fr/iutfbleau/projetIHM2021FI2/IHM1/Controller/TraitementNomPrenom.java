package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import java.awt.event.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.IHM1.Modele.*;

/**
 * <code>TraitementNomPrenom</code> est un contrôleur.
 * Il gère le bouton lancer du menu
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */

public class TraitementNomPrenom implements ActionListener{
    private JFrame fenetre;
    private JTextField prenom;
    private JTextField nom;
    
    /**
     * Constructeur qui permer d'accéder à la fenêtre par la suite
     * @param fenetre the linked window
     * @param nom
     * @param prenom
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
        new VerificationNomPrenom(this.fenetre, this.nom.getText(), this.prenom.getText());
    }
    
    
}