package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import javax.print.attribute.standard.PresentationDirection;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.PrereservationFactory;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Modele.*;

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
    private PrereservationFactory bookingPointComAPISeulement;
    
    /**
     * Constructeur qui permer d'accéder à la fenêtre par la suite
     * @param bookingPointComAPISeulement
     * @param fenetre the linked window
     * @param reference
     */
    public TraitementReference(PrereservationFactory bookingPointComAPISeulement, JFrame fenetre, JTextField reference) {
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
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
        new VerificationReference(this.bookingPointComAPISeulement, this.fenetre, this.reference.getText());
    }
    
    
}