package fr.iutfbleau.projetIHM2021FI2.Vues;
import javax.swing.*;
import java.awt.event.*;

/**
 * <code>TraitementReference</code> est un contrôleur.
 * Il gère le bouton lancer du menu
 * 
 * @author Enora GERMOND
 * @version 1.3
 */

public class TraitementReference implements ActionListener{
    private JFrame fenetre;
    
    /**
     * Constructeur qui permer d'accéder à la fenêtre par la suite
     * @param fenetre the linked window
     */
    public TraitementReference(JFrame fenetre) {
        this.fenetre = fenetre;
    }

    /**
     * Lors du clic sur le bouton
     * @param e the action data
     */
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println("Référence");
        new FormulaireReference();
        /* Le truc à changer
        fenetre.dispose();

        //Appel de la fenetre de jeu
        new Jeu(); */
    }
    
    
}