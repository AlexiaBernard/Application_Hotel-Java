package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Modele.*;

import javax.swing.*;


/**
 * <code>TraitementNomPrenom</code> est un contrôleur.
 * Il gère le bouton lancer du menu
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */

public class TraitementValider{

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private Prereservation prereservation;
    private Chambre chambre;
    private JPanel centre;
    
    /**
     * Constructeur qui permer d'accéder à la fenêtre par la suite
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement
     * @param fenetre 
     * @param prereservation
     * @param chambre
     */
    public TraitementValider(PrereservationFactory bookingPointComAPISeulement, ReservationFactory grandLivreDOrAPISeulement,  JFrame fenetre, Chambre chambre, Prereservation prereservation) {
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.prereservation = prereservation;
        this.chambre = chambre;
    }
    
    public void run(){
        AjoutReservation reser = new AjoutReservation(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, this.fenetre, this.chambre, this.prereservation, this.centre);
        reser.run();
    }
}
