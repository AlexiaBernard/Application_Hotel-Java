package fr.iutfbleau.projetIHM2021FI2.IHM1.Model;

import java.util.Set;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.View.*;

/**
 * <code>AjoutReservation</code> est un modèle.
 * Il sert à ajouter une réservation à partir d'une préréservation dans la base de données.
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
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
     * Constructeur permettant d'accéder à une préréservation
     * 
     * @param bookingPointComAPISeulement interface PreservationFactory de l'API
     *                                    correspondant à la préreservation
     * @param grandLivreDOrAPISeulement   modèle non persistant de Réservation
     * @param fenetre                     la fenetre
     * @param chambre                     une chambre
     * @param prereservation              une préreservation
     * @param centre                      le centre de la fenetre
     * @param disponibles                 chambres disponibles
     * @param i                           i
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

    /**
     * Crée une réservation dans la base de données et appelle Fin pour afficher un dialogue de validation.
     */
    public void run(){
        try{
            this.grandLivreDOrAPISeulement.createReservation(this.prereservation,this.chambre);
            Fin fin = new Fin(this.bookingPointComAPISeulement,this.grandLivreDOrAPISeulement, 
                    this.fenetre, this.centre);
            fin.run();
        }catch(IllegalStateException e){
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