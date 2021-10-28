package fr.iutfbleau.projetIHM2021FI2.IHM1.Model;

import java.util.Set;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.View.*;

public class AjoutReservation {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private Chambre chambre;
    private Prereservation prereservation;
    private JPanel centre;
    private Set<Chambre> disponibles;
    private int i;
    
    /**
     * 
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement
     * @param fenetre
     * @param chambre
     * @param prereservation
     * @param centre
     * @param disponibles
     * @param i
     */
    public AjoutReservation(PrereservationFactory bookingPointComAPISeulement, 
                ReservationFactory grandLivreDOrAPISeulement,  JFrame fenetre, 
                Chambre chambre, Prereservation prereservation, JPanel centre, 
                Set<Chambre> disponibles, int i){
       this.bookingPointComAPISeulement = bookingPointComAPISeulement;
       this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
       this.fenetre = fenetre;
       this.chambre = chambre;
       this.prereservation = prereservation;
       this.centre = centre;
       this.disponibles = disponibles;
       this.i = i;
    }

    public void run(){
        try{
            this.grandLivreDOrAPISeulement.createReservation(this.prereservation,this.chambre);
            Fin fin = new Fin(this.bookingPointComAPISeulement,this.grandLivreDOrAPISeulement, 
                    this.fenetre, this.centre);
            fin.run();
        }
        catch(IllegalStateException e){
            JOptionPane.showMessageDialog(this.fenetre,"L'ajout de la réservation a échoué.");
            if(i==0){
                AfficherListe aff = new AfficherListe(this.bookingPointComAPISeulement, 
                        this.grandLivreDOrAPISeulement, this.fenetre, prereservation, this.disponibles);
                aff.run();
            } else if(i==1){
                Afficher aff = new Afficher(this.bookingPointComAPISeulement, 
                        this.grandLivreDOrAPISeulement, this.fenetre, this.chambre, 
                        this.prereservation, this.centre);
                aff.run();
            }
        }
    }
}
