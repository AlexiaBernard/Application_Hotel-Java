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
    
    /**
     * Constructeur qui permer d'accéder à la fenêtre par la suite
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement
     * @param fenetre the linked window
     * @param prereservation
     * @param chambre
     */
    public TraitementValider(PrereservationFactory bookingPointComAPISeulement, ReservationFactory grandLivreDOrAPISeulement,  JFrame fenetre, Chambre chambre, Prereservation prereservation) {
        new AjoutReservation(bookingPointComAPISeulement, grandLivreDOrAPISeulement, fenetre, chambre, prereservation);
    }
    
}
