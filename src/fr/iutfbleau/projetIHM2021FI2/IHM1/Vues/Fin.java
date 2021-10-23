package fr.iutfbleau.projetIHM2021FI2.IHM1.Vues;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Controller.*;

public class Fin {
    
    public Fin(PrereservationFactory bookingPointComAPISeulement, ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre){
        int result = JOptionPane.showConfirmDialog(fenetre, "Réservation Validée, voulez-vous retourner au menu?");

        if(result==0){
            new TraitementRetourMenu(bookingPointComAPISeulement, grandLivreDOrAPISeulement,fenetre);
        }else{
            new TraitementFin(fenetre);
        }
    }
}