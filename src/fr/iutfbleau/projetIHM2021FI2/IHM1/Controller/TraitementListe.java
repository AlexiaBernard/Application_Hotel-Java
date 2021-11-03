package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import java.util.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.View.*;

/**
 * <code>TraitementListe</code> est un contrôleur.
 * Il gère le bouton ...
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
     * Constructeur qui permer d'accéder à la fenêtre et à la préreservation en paramètre par la suite
     * @param bookingPointComAPISeulement la préréservation
     * @param grandLivreDOrAPISeulement
     * @param fenetre la fenêtre
     * @param prereservation
     */
    public TraitementListe(PrereservationFactory bookingPointComAPISeulement, 
            ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, 
            Prereservation prereservation) {
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.prereservation = prereservation;
    }   

    public void run(){
        Set<Chambre> disponibles=null;
        try{
            disponibles = this.grandLivreDOrAPISeulement.getChambres(this.prereservation);
            AfficherListe liste = new AfficherListe(this.bookingPointComAPISeulement,
                    this.grandLivreDOrAPISeulement, this.fenetre, this.prereservation, disponibles);
            liste.run();            
        }
        catch(IllegalStateException e){
            JOptionPane.showMessageDialog(this.fenetre,"Problème avec la base de données.");
            TraitementFin fin = new TraitementFin(this.grandLivreDOrAPISeulement ,this.fenetre);
            fin.run();
        }      
}
}