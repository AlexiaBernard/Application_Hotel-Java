package fr.iutfbleau.projetIHM2021FI2.IHM1.Vues;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Controller.*;


public class Afficher {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private Chambre chambre;
    private Prereservation prereservation;
    private JPanel centre;

    /**
     * 
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement
     * @param fenetre
     * @param ch1
     * @param prereservation
     * @param centre
     */
    public Afficher(PrereservationFactory bookingPointComAPISeulement, ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre,Chambre ch1, Prereservation prereservation, JPanel centre){
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.chambre = ch1;
        this.prereservation = prereservation;    
        this.centre = centre;   
    }

    public void run(){
        int result = JOptionPane.showConfirmDialog(this.fenetre, "La chambre numéro "+this.chambre.getNumero()+" est disponible. Vous convient-elle?");

        if(result==0){
            TraitementValider valider = new TraitementValider(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement ,this.fenetre, this.chambre, this.prereservation);
            valider.run();
        }else if(result==1){
            AfficherReservations aff = new AfficherReservations(bookingPointComAPISeulement, grandLivreDOrAPISeulement, fenetre, null);
            aff.deleteRef(this.centre);
            TraitementListe liste = new  TraitementListe(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, this.fenetre, this.prereservation);
            liste.run();
        }else{
            TraitementFin fin = new TraitementFin(this.fenetre);
            fin.run();
        }
    }
    
}