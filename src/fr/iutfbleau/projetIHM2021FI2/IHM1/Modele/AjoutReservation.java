package fr.iutfbleau.projetIHM2021FI2.IHM1.Modele;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Vues.*;

public class AjoutReservation {
    
    public AjoutReservation(PrereservationFactory bookingPointComAPISeulement, ReservationFactory grandLivreDOrAPISeulement,  JFrame fenetre, Chambre chambre, Prereservation prereservation){
        try{
            grandLivreDOrAPISeulement.createReservation(prereservation,chambre);
            System.out.println("Réservation validée.");
            new Fin(bookingPointComAPISeulement,grandLivreDOrAPISeulement, fenetre);
        }
        catch(IllegalStateException e){
            System.out.print(e.getMessage());
        }
       
    }
}
