package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Model.*;

/**
 * <code>TraitementValider</code> est un contrôleur.
 * Il gère le bouton <b>Oui</b> suite à la dialogue de confirmation
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
     * Constructeur qui permer d'accéder à la fenêtre et à la préreservation en
     * paramètre par la suite
     * 
     * @param bookingPointComAPISeulement interface PreservationFactory de l'API
     *                                    correspondant à la préreservation
     * @param grandLivreDOrAPISeulement   modèle non persistant de Réservation
     * @param fenetre                     la fenêtre
     * @param prereservation              la préreservation
     * @param chambre                     la chambre
     */
    public TraitementValider(PrereservationFactory bookingPointComAPISeulement, 
            ReservationFactory grandLivreDOrAPISeulement,  JFrame fenetre, 
            Chambre chambre, Prereservation prereservation) {
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.prereservation = prereservation;
        this.chambre = chambre;
    }
    
    /**
     * Exécution du bouton : appel de <b>AjoutReservation</b>
     */
    public void run(){
        AjoutReservation reser = new AjoutReservation(this.bookingPointComAPISeulement, 
                this.grandLivreDOrAPISeulement, this.fenetre, this.chambre, 
                this.prereservation, this.centre, null,1);
        reser.run();
    }
}
