package fr.iutfbleau.projetIHM2021FI2.IHM2.Model;

import java.time.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.AfficherRatio;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.Bureau;

public class VerificationDate {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private LocalDate date;
    private JPanel centre;    

    /**
     * 
     * @param fenetre
     * @param grandLivreDOrAPISeulement
     * @param date
     * @param centre
     */
    public VerificationDate(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, LocalDate date, JPanel centre) {
        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.date = date;
        this.centre = centre;
    }

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
