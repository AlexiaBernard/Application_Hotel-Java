package fr.iutfbleau.projetIHM2021FI2.IHM1.Model;

import java.util.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.View.*;

/**
 * <code>AjoutReservation</code> est un modèle.
 * Il sert à vérifier la correspondance entre les infos du client et une préréservation dans la base de données.
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class VerificationNomPrenom {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private JPanel centre;
    private String nom;
    private String prenom;

    /**
     * Constructeur permettant d'accéder aux informations sur le client
     * 
     * @param bookingPointComAPISeulement interface PreservationFactory de l'API
     *                                    correspondant à la préreservation
     * @param grandLivreDOrAPISeulement   modèle non persistant de Réservation
     * @param fenetre                     la fenetre
     * @param centre                      le centre de la fenetre
     * @param nom                         le nom du client
     * @param prenom                      le prénom du client
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

    /**
     * Vérification dans la BD si le client existe & a fait une préréservation
     */
    public void run(){
        try{
            Set<Prereservation> prereservations = this.bookingPointComAPISeulement.getPrereservations(this.nom, this.prenom);
            if (prereservations.size() == 0){
                throw new IllegalStateException("Il n'y a pas de préréservation pour ce client");
            }
            if (this.centre != null){
                this.centre.removeAll();
            }
            AfficherReservations rese = new AfficherReservations(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, 
                    this.fenetre, this.centre, prereservations);
            rese.run();
        }catch(IllegalStateException e){
            JOptionPane.showMessageDialog(this.fenetre,"Pas de préréservation pour ce client.");
            Menu menu = new Menu(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, this.fenetre, this.centre);
            menu.run();
        }    
    }
}