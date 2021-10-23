package fr.iutfbleau.projetIHM2021FI2.IHM1.Vues;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Controller.*;


public class Afficher {

    public Afficher(PrereservationFactory bookingPointComAPISeulement, ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre,Chambre ch1, Prereservation prereservation){

        int result = JOptionPane.showConfirmDialog(fenetre, "Lachambre numéro "+ch1.getNumero()+" est disponible. Vous convient-elle?");

        if(result==0){
            new TraitementValider(bookingPointComAPISeulement, grandLivreDOrAPISeulement ,fenetre, ch1, prereservation);
        }else if(result==1){
            new  TraitementListe(bookingPointComAPISeulement, grandLivreDOrAPISeulement, fenetre);
        }else{
            new TraitementFin(fenetre);
        }        
    }
    
}
