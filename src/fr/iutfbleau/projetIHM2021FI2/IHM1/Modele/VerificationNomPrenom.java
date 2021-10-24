package fr.iutfbleau.projetIHM2021FI2.IHM1.Modele;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Vues.*;
import fr.iutfbleau.projetIHM2021FI2.MNP.*;

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
            for(Prereservation p : prereservations){
                System.out.println(p.monPrint());
                //Chambre libre = null;
                Set<Chambre> disponibles=null;
                try{
                    disponibles = this.grandLivreDOrAPISeulement.getChambres(p);
                    AfficherListe liste = new AfficherListe(this.bookingPointComAPISeulement,this.grandLivreDOrAPISeulement, this.fenetre, p, disponibles);
                    liste.run();
                    /*for(Chambre c : disponibles){
                        System.out.println(c.monPrint());
                    } Ca C'est La vue !! 
                    */
                    
                }
                catch(IllegalStateException e){
                    System.out.print(e.getMessage());
                }
               
        
            }
        }catch(IllegalStateException e){
            System.out.print("Je n'ai pas trouvé de préreservation avec ces nom et prénom.\n");
            Menu menu = new Menu(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, this.fenetre, 2);
            menu.run();
        }    
    }
}
/*
                    // choix de la chambre au hasard simulant le choix du client
                    int choix = random.nextInt(disponibles.size());
                    System.out.println("choix: " + choix);
                    int compter = 0;    
                    for(Chambre c : disponibles){
                        if (compter == choix){
                            libre = c;
                            break;
                        }
                        compter++;
                    }
                     try{
                    System.out.println(libre.monPrint());
                    System.out.print("You like this room? Excellent! Let me confirm the reservation for you.\n");
                    Reservation resa = grandLivreDOrAPISeulement.createReservation(p,libre);
                    System.out.println(resa.monPrint());
                }
                catch(IllegalStateException e){
                    System.out.print(e.getMessage());
                }
                    */