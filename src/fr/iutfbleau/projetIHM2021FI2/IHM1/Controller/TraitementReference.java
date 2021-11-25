package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Model.*;

import java.awt.event.*;

/**
 * <code>TraitementReference</code> est un contrôleur.
 * Il gère le bouton Valider après la référence
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class TraitementReference implements ActionListener{
    
    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private JPanel centre;
    private JTextField reference;
    
    /**
     * Constructeur permettant d'accéder à la chambre sélectionnée dans la liste des
     * chambres libres par la suite
     * 
     * @param bookingPointComAPISeulement interface PreservationFactory de l'API
     *                                    correspondant à la préreservation
     * @param grandLivreDOrAPISeulement   modèle non persistant de Réservation
     * @param fenetre                     la fenetre
     * @param centre                      centre de la fenetre
     * @param reference                   reference de la préreservation
     */
    public TraitementReference(PrereservationFactory bookingPointComAPISeulement, 
            ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, JPanel centre, JTextField reference) {
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.centre = centre;
        this.reference = reference;
    }

    /**
     * Lors du clic sur le bouton
     * @param e the action data
     */
    @Override
    public void actionPerformed(ActionEvent e){
        VerificationReference verif = new VerificationReference(this.bookingPointComAPISeulement, 
                this.grandLivreDOrAPISeulement, this.fenetre, this.reference.getText(), this.centre);
        this.reference.setText("");
        verif.run();
    }
}