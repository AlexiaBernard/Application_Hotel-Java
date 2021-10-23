package fr.iutfbleau.projetIHM2021FI2.IHM1.Modele;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Vues.*;

import javax.swing.*;

/**
 * 
 */
public class VerificationReference {

    /**
     * 
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement
     * @param fenetre
     * @param reference
     */
    public VerificationReference(PrereservationFactory bookingPointComAPISeulement, ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, String reference){
   
        try{
            Prereservation prereservation = bookingPointComAPISeulement.getPrereservation(reference);
            System.out.println(reference);
            Chambre libre=null;
            try{
                libre = grandLivreDOrAPISeulement.getChambre(prereservation);
                System.out.print("J'ai trouvé une chambre.\n");
                System.out.println(libre.monPrint());
            }
            catch(IllegalStateException e){
                System.out.print(e.getMessage());
            }
            new Afficher(bookingPointComAPISeulement, grandLivreDOrAPISeulement, fenetre, libre, prereservation);
        }catch(IllegalStateException e){
            System.out.print("Je n'ai pas trouvé de préreservation avec cette référence.\n");
            new Menu(bookingPointComAPISeulement, grandLivreDOrAPISeulement,  fenetre, 1);
        }
   }  
}
