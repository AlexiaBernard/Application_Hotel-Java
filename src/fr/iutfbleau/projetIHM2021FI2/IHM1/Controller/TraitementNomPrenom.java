package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import java.awt.event.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Model.*;

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
    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    
    /**
     * Constructeur qui permer d'accéder à la fenêtre par la suite
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement
     * @param fenetre the linked window
     * @param nom
     * @param prenom
     */
    public TraitementNomPrenom(PrereservationFactory bookingPointComAPISeulement, 
            ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, 
            JTextField nom, JTextField prenom) {
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
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
        VerificationNomPrenom np = new VerificationNomPrenom(this.bookingPointComAPISeulement, 
                this.grandLivreDOrAPISeulement, this.fenetre, this.nom.getText(), this.prenom.getText());
        this.nom.setText("");
        this.prenom.setText("");
        np.run();
    }
    
    
}