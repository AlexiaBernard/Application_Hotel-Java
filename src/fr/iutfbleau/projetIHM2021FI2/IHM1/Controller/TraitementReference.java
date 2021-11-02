package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Model.VerificationReference;

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
    private ReservationFactory grandLivreDOrAPISeulement;
    
    /**
     * Constructeur qui permer d'accéder à la fenêtre par la suite
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement
     * @param fenetre the linked window
     * @param reference
     */
    public TraitementReference(PrereservationFactory bookingPointComAPISeulement, 
            ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, JTextField reference) {
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.reference = reference;
    }

    /**
     * Lors du clic sur le bouton
     * @param e the action data
     */
    @Override
    public void actionPerformed(ActionEvent e){
        JPanel centre = new JPanel();
        VerificationReference verif = new VerificationReference(this.bookingPointComAPISeulement, 
                this.grandLivreDOrAPISeulement, this.fenetre, this.reference.getText(), centre);
        verif.run();
    }
}