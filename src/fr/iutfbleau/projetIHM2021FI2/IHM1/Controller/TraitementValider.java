package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Modele.*;

import javax.swing.*;

import java.awt.event.*;


/**
 * <code>TraitementNomPrenom</code> est un contrôleur.
 * Il gère le bouton lancer du menu
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */

public class TraitementValider implements ActionListener{
    private JFrame fenetre;
    private Chambre chambre;
    private Prereservation prereservation;
    private PrereservationFactory bookingPointComAPISeulement;

    
    /**
     * Constructeur qui permer d'accéder à la fenêtre par la suite
     * @param bookingPointComAPISeulement
     * @param fenetre the linked window
     * @param prereservation
     * @param chambre
     */
    public TraitementValider(PrereservationFactory bookingPointComAPISeulement, JFrame fenetre, Chambre chambre, Prereservation prereservation) {
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.fenetre = fenetre;
        this.chambre = chambre;
        this.prereservation = prereservation;
    }

    /**
     * Lors du clic sur le bouton
     * @param e the action data
     */
    @Override
    public void actionPerformed(ActionEvent a){
        //Ici on attend que le professeur créé ResreationFactoryNP
        //ReservationFactory chambresReservees = new ReservationFactoryNP();
        //chambresReservees.createReservation(prereservation, chambre);
        System.out.println("Réservation validée");
        new AjoutReservation(bookingPointComAPISeulement, fenetre, chambre,prereservation);
    }
    
}
