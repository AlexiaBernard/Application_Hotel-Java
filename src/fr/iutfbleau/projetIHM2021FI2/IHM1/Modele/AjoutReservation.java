package fr.iutfbleau.projetIHM2021FI2.IHM1.Modele;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Vues.*;

public class AjoutReservation {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private Chambre chambre;
    private Prereservation prereservation;
    
    /**
     * 
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement
     * @param fenetre
     * @param chambre
     * @param prereservation
     */
    public AjoutReservation(PrereservationFactory bookingPointComAPISeulement, ReservationFactory grandLivreDOrAPISeulement,  JFrame fenetre, Chambre chambre, Prereservation prereservation){
       this.bookingPointComAPISeulement = bookingPointComAPISeulement;
       this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
       this.fenetre = fenetre;
       this.chambre = chambre;
       this.prereservation = prereservation;
    }

    public void run(){
        try{
            this.grandLivreDOrAPISeulement.createReservation(this.prereservation,this.chambre);
            System.out.println("Réservation validée.");
            Fin fin = new Fin(this.bookingPointComAPISeulement,this.grandLivreDOrAPISeulement, this.fenetre);
            fin.run();
        }
        catch(IllegalStateException e){
            System.out.print(e.getMessage());
        }
    }
}
