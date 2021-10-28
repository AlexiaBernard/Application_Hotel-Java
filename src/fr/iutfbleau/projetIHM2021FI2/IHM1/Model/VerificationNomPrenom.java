package fr.iutfbleau.projetIHM2021FI2.IHM1.Model;

import java.util.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.View.*;

public class VerificationNomPrenom {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private String nom;
    private String prenom;

    /**
     * 
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement
     * @param fenetre
     * @param nom
     * @param prenom
     */
    public VerificationNomPrenom(PrereservationFactory bookingPointComAPISeulement, ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, String nom, String prenom){
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.nom = nom;
        this.prenom = prenom;      
    }

    public void run(){
        try{
            Set<Prereservation> prereservations = this.bookingPointComAPISeulement.getPrereservations(this.nom, this.prenom);
            AfficherReservations rese = new AfficherReservations(bookingPointComAPISeulement, grandLivreDOrAPISeulement, fenetre, prereservations);
            rese.run();
        }catch(IllegalStateException e){
            JOptionPane.showMessageDialog(this.fenetre,"Pas de préréservation pour ce client.");
            Menu menu = new Menu(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, this.fenetre);
            menu.run();
        }    
    }
}