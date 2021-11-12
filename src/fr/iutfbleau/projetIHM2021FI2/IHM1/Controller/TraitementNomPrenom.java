package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import java.awt.event.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Model.*;

/**
 * <code>TraitementNomPrenom</code> est un contrôleur.
 * Il gère le bouton Valider en dessous de nom et prénom
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */

public class TraitementNomPrenom implements ActionListener{

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private JPanel centre;
    private JTextField prenom;
    private JTextField nom;

    /**
     * 
     * @param bookingPointComAPISeulement interface PreservationFactory de l'API
     *                                    correspondant à la préreservation
     * @param grandLivreDOrAPISeulement   modèle non persistant de Réservation
     * @param fenetre                     la fenetre
     * @param centre                      centre de la fenetre
     * @param nom                         nom du client
     * @param prenom                      prénom du client
     */
    public TraitementNomPrenom(PrereservationFactory bookingPointComAPISeulement, 
            ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, 
            JPanel centre, JTextField nom, JTextField prenom) {
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.centre = centre;
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
                this.grandLivreDOrAPISeulement, this.fenetre, this.centre, this.nom.getText(), this.prenom.getText());
        this.nom.setText("");
        this.prenom.setText("");
        np.run();
    }
    
    
}