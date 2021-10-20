package fr.iutfbleau.projetIHM2021FI2.Controller;

import fr.iutfbleau.projetIHM2021FI2.Modele.*;
import javax.swing.*;

import java.awt.event.*;

/**
 * <code>TraitementReference</code> est un contrôleur.
 * Il gère le bouton lancer du menu
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */

public class TraitementReference implements ActionListener{
    private JFrame fenetre;
    private JTextField reference;
    
    /**
     * Constructeur qui permer d'accéder à la fenêtre par la suite
     * @param fenetre the linked window
     * @param reference
     */
    public TraitementReference(JFrame fenetre, JTextField reference) {
        this.fenetre = fenetre;
        this.reference = reference;
    }

    /**
     * Lors du clic sur le bouton
     * @param e the action data
     */
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println("Référence : " + this.reference.getText());
        new VerificationReference(this.fenetre, this.reference.getText());
    }
    
    
}