package fr.iutfbleau.projetIHM2021FI2.IHM1.Modele;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Vues.*;

import javax.swing.*;

/**
 * 
 */
public class VerificationReference {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private String reference;

    /**
     * 
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement
     * @param fenetre
     * @param reference
     */
    public VerificationReference(PrereservationFactory bookingPointComAPISeulement, ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, String reference){
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.reference = reference; 
        
   }
   
   public void run(){
    try{
        Prereservation prereservation = this.bookingPointComAPISeulement.getPrereservation(this.reference);
        System.out.println(this.reference);
        Chambre libre=null;
        try{
            libre = this.grandLivreDOrAPISeulement.getChambre(prereservation);
            System.out.print("J'ai trouvé une chambre.\n");
            System.out.println(libre.monPrint());
        }
        catch(IllegalStateException e){
            System.out.print(e.getMessage());
        }
        Afficher afficher = new Afficher(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, this.fenetre, libre, prereservation);
        afficher.run();
    }catch(IllegalStateException e){
        System.out.print("Je n'ai pas trouvé de préreservation avec cette référence.\n");
        Menu menu = new Menu(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement,  this.fenetre, 1);
        menu.run();
    }
   }
}
