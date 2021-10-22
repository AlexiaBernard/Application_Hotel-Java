package fr.iutfbleau.projetIHM2021FI2.IHM1.Modele;

import fr.iutfbleau.projetIHM2021FI2.MNP.*;
import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Vues.*;

import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;

/**
 * 
 */
public class VerificationReference {

    /**
     * 
     * @param bookingPointComAPISeulement2
     * @param fenetre
     * @param reference
     */
    public VerificationReference(PrereservationFactory bookingPointComAPISeulement, JFrame fenetre, String reference){
        TypeChambre type2LS = TypeChambre.DEUXLS; //utilisation type énuméré TypeChambre
        Chambre ch1 = new ChambreNP(1, type2LS);

        try{
            Prereservation prereservation = bookingPointComAPISeulement.getPrereservation(reference);
            System.out.println(reference);
            new Afficher(bookingPointComAPISeulement, fenetre, ch1, prereservation);
        }catch(IllegalStateException e){
            System.out.print("Je n'ai pas trouvé de préreservation avec cette référence.\n");
            new Menu(bookingPointComAPISeulement, fenetre, 1);
        }
   }  
}
