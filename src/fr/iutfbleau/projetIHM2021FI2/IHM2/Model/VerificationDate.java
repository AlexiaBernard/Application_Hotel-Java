package fr.iutfbleau.projetIHM2021FI2.IHM2.Model;

import java.time.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.AfficherRatio;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.Bureau;

/**
 * <code>VerificationDate</code> est un modèle.
 * Il sert à calculer le ratio d'une date donnée.
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class VerificationDate {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private LocalDate date;
    private JPanel centre;    

    /**
     * Constructeur permettant d'accéder à une date
     * 
     * @param grandLivreDOrAPISeulement modèle non persistant de Réservation
     * @param fenetre                   la fenetre
     * @param date                      la date
     * @param centre                    le centre de la fenetre
     */
    public VerificationDate(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, LocalDate date, JPanel centre) {
        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.date = date;
        this.centre = centre;
    }

    /**
     * Vérification de l'état de la date donnée et appel de la fonction pour
     * l'affichage du ratio
     */
    public void run(){
        try {
            int ratio = grandLivreDOrAPISeulement.getRatio(this.date);
            AfficherRatio aff = new AfficherRatio(this.fenetre, this.grandLivreDOrAPISeulement, this.date, ratio, this.centre);
            if (this.centre != null)
                aff.deleteCentre();
            aff.run();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(fenetre,"Problème de calcul du ratio pour cette date.");
            Bureau bureau = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
            bureau.run();
        }
    }
    
}
