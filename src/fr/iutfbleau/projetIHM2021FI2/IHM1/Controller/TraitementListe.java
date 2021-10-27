package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import java.util.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Vues.*;




/**
 * <code>TraitementNomPrenom</code> est un contrôleur.
 * Il gère le bouton lancer du menu
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */

public class TraitementListe{

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private Prereservation prereservation;
    
    /**
     * Constructeur qui permer d'accéder à la fenêtre par la suite
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement
     * @param fenetre
     * @param prereservation
     */
    public TraitementListe(PrereservationFactory bookingPointComAPISeulement, ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, Prereservation prereservation) {
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.prereservation = prereservation;
    }   

    public void run(){
        Set<Chambre> disponibles=null;
        try{
            disponibles = this.grandLivreDOrAPISeulement.getChambres(this.prereservation);
            AfficherListe liste = new AfficherListe(this.bookingPointComAPISeulement,this.grandLivreDOrAPISeulement, this.fenetre, this.prereservation, disponibles);
            liste.run();            
        }
        catch(IllegalStateException e){
            System.out.print(e.getMessage());
        }      
}
}