package fr.iutfbleau.projetIHM2021FI2.IHM1.Model;

import java.util.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.View.*;

public class VerificationNomPrenom {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private JPanel centre;
    private String nom;
    private String prenom;

    /**
     * 
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement
     * @param fenetre
     * @param centre
     * @param nom
     * @param prenom
     */
    public VerificationNomPrenom(PrereservationFactory bookingPointComAPISeulement, 
            ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, JPanel centre, String nom, String prenom){
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.centre = centre;
        this.nom = nom;
        this.prenom = prenom;      
    }

    public void run(){
        try{
            Set<Prereservation> prereservations = this.bookingPointComAPISeulement.getPrereservations(this.nom, this.prenom);
            if (prereservations.size() == 0){
                JOptionPane.showMessageDialog(this.fenetre,"Pas de préréservation pour ce client.");
                Menu menu = new Menu(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, this.fenetre, this.centre);
                menu.run();
            }
            AfficherReservations rese = new AfficherReservations(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, this.fenetre, this.centre, prereservations);
            rese.run();
        }catch(IllegalStateException e){
            JOptionPane.showMessageDialog(this.fenetre,"Pas de préréservation pour ce client.");
            Menu menu = new Menu(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, this.fenetre, this.centre);
            menu.run();
        }    
    }
}