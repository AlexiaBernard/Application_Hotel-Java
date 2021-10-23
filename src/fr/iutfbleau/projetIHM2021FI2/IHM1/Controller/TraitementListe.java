package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;


/**
 * <code>TraitementNomPrenom</code> est un contrôleur.
 * Il gère le bouton lancer du menu
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */

public class TraitementListe{
    
    /**
     * Constructeur qui permer d'accéder à la fenêtre par la suite
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement the linked window
     * @param fenetre2
     */
    public TraitementListe(PrereservationFactory bookingPointComAPISeulement, ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre) {
        System.out.println("Liste des chambres disponibles....");
    }    
}