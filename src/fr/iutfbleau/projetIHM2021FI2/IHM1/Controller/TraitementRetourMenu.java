package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Vues.*;

public class TraitementRetourMenu {

    public TraitementRetourMenu(PrereservationFactory bookingPointComAPISeulement, ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre) {
        new Menu(bookingPointComAPISeulement, grandLivreDOrAPISeulement, fenetre, 0);
    }
}
