package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import javax.swing.*;

import java.awt.event.*;

/**
 * <code>TraitementNomPrenom</code> est un contrôleur.
 * Il gère le bouton lancer du menu
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */

public class TraitementListe implements ActionListener{
    private JFrame fenetre;
    
    /**
     * Constructeur qui permer d'accéder à la fenêtre par la suite
     * @param fenetre the linked window
     */
    public TraitementListe(JFrame fenetre) {
        this.fenetre = fenetre;
    }

    /**
     * Lors du clic sur le bouton
     * @param e the action data
     */
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println("Liste des chambres disponibles....");
    }
    
    
}