package fr.iutfbleau.projetIHM2021FI2.IHM1.View;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Controller.*;

public class Fin {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private JPanel centre;
    
    /**
     * 
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement
     * @param fenetre
     * @param centre
     */
    public Fin(PrereservationFactory bookingPointComAPISeulement, ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, JPanel centre){
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.centre = centre;
    }

    public void run(){
        int result = JOptionPane.showConfirmDialog(this.fenetre, "Réservation Validée, voulez-vous retourner au menu?");

        if(result==0){
            AfficherListe aff = new AfficherListe(bookingPointComAPISeulement, grandLivreDOrAPISeulement, fenetre, null, null);
            aff.deleteRef(centre);
            TraitementRetourMenu menu = new TraitementRetourMenu(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement,this.fenetre);
            menu.run();
        }else{
            TraitementFin fin = new TraitementFin(this.fenetre);
            fin.run();
        }
    }
}