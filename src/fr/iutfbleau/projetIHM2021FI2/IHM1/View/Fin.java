package fr.iutfbleau.projetIHM2021FI2.IHM1.View;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Controller.*;

/**
 * <code>AfficherFin</code> est une vue
 * Elle affiche la liste des réservations faites par une personne qui a donné son nom et prénom
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class Fin {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private JPanel centre;
    
    /**
     *Constructeur permettant d'accéder à la liste des réservations effectuées par une personne
     * @param bookingPointComAPISeulement interface PreservationFactory de l'API correspondant à la préreservation
     * @param grandLivreDOrAPISeulement modèle non persistant de Réservation
     * @param fenetre la fenetre
     * @param centre centre de la fenetre
     */
    public Fin(PrereservationFactory bookingPointComAPISeulement, 
            ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, JPanel centre){
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.centre = centre;
    }

    /**
     * Afiche une boite de dialogue 
     */
    public void run(){
        int result = JOptionPane.showConfirmDialog(this.fenetre, "Réservation Validée, voulez-vous retourner au menu?");

        if(result==0){
            if (this.centre !=null){
                AfficherListe aff = new AfficherListe(bookingPointComAPISeulement, grandLivreDOrAPISeulement, fenetre, null, null);
                aff.deleteRef(centre);
            }
            TraitementRetourMenu menu = new TraitementRetourMenu(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement,this.fenetre, this.centre);
            menu.run();
        }else{
            TraitementFin fin = new TraitementFin(this.grandLivreDOrAPISeulement, this.fenetre);
            fin.run();
        }
    }
}