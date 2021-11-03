package fr.iutfbleau.projetIHM2021FI2.IHM1.View;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Controller.*;

/**
 * <code>Afficher</code> est une vue
 * Elle affiche une boite de dialogue lorsqu'une préreservation a été trouvée 
 */
public class Afficher {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private Chambre chambre;
    private Prereservation prereservation;
    private JPanel centre;

    /**
     * Constructeur qui permer d'accéder à la fenêtre à la chambre et à la préreservation en paramètre par la suite
     * @param bookingPointComAPISeulement interface PreservationFactory de l'API correspondant à la préreservation
     * @param grandLivreDOrAPISeulement modèle non persistant de Réservation
     * @param fenetre la fenetre
     * @param ch1 une chambre
     * @param prereservation une préréservation
     * @param centre un JPanel
     */
    public Afficher(PrereservationFactory bookingPointComAPISeulement, 
                ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre,
                Chambre ch1, Prereservation prereservation, JPanel centre){
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.chambre = ch1;
        this.prereservation = prereservation;    
        this.centre = centre;   
    }

    /**
     * Affiche une boite de dialogue lorsqu'une préreservation a été trouvée afin de savoir quelle action effectuer
     */
    public void run(){
        int result = JOptionPane.showConfirmDialog(this.fenetre, 
                "La chambre numéro "+this.chambre.getNumero()+" est disponible. Vous convient-elle?");

        if(result==0){
            TraitementValider valider = new TraitementValider(this.bookingPointComAPISeulement, 
                    this.grandLivreDOrAPISeulement ,this.fenetre, this.chambre, this.prereservation);
            valider.run();
        }else if(result==1){
            AfficherReservations aff = new AfficherReservations(this.bookingPointComAPISeulement, 
                    this.grandLivreDOrAPISeulement, this.fenetre, null);
            aff.deleteRef(this.centre);
            TraitementListe liste = new  TraitementListe(this.bookingPointComAPISeulement, 
                    this.grandLivreDOrAPISeulement, this.fenetre, this.prereservation);
            liste.run();
        }else{
            TraitementFin fin = new TraitementFin(this.grandLivreDOrAPISeulement, this.fenetre);
            fin.run();
        }
    }
    
}