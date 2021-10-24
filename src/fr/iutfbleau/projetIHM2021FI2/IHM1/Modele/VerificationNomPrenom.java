package fr.iutfbleau.projetIHM2021FI2.IHM1.Modele;

import java.util.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Vues.*;

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
            System.out.println(this.nom + " " + this.prenom);
            AfficherReservations rese = new AfficherReservations(bookingPointComAPISeulement, grandLivreDOrAPISeulement, fenetre, prereservations);
            rese.run();
        }catch(IllegalStateException e){
            System.out.print("Je n'ai pas trouvé de préreservation avec ces nom et prénom.\n");
            Menu menu = new Menu(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, this.fenetre, 2);
            menu.run();
        }    
    }
}