package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.View.*;

public class TraitementRetourMenu {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private  JPanel centre;

    /**
     * 
     * @param bookingPointComAPISeulement interface PreservationFactory de l'API
     *                                    correspondant à la préreservation
     * @param grandLivreDOrAPISeulement   modèle non persistant de Réservation
     * @param fenetre                     la fenetre
     * @param centre                      centre de la fenetre
     */
    public TraitementRetourMenu(PrereservationFactory bookingPointComAPISeulement, 
            ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, JPanel centre) {
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
    }

    public void run(){
        Menu menu = new Menu(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, this.fenetre, this.centre);
        menu.run();
    }
}
