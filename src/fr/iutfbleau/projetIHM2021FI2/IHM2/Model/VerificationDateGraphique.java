package fr.iutfbleau.projetIHM2021FI2.IHM2.Model;

import java.time.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.AfficherGraphique;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.Bureau;

public class VerificationDateGraphique {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private LocalDate dateDeb;
    private LocalDate dateFin;
    private int retour;
    private JPanel centre;

    /**
     * 
     * @param fenetre
     * @param grandLivreDOrAPISeulement
     * @param dateDeb
     * @param dateFin
     * @param retour
     * @param centre
     */
    public VerificationDateGraphique(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement,
            LocalDate dateDeb, LocalDate dateFin, int retour, JPanel centre) {
        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.retour = retour;
        this.centre = centre;
    }

    public void run() {
        try {
            int ratio = grandLivreDOrAPISeulement.getRatio(this.dateDeb,this.dateFin);
            int ratioComp = grandLivreDOrAPISeulement.getRatio(this.dateDeb, this.dateDeb.minusYears(3));
            AfficherGraphique aff = new AfficherGraphique(this.fenetre, this.grandLivreDOrAPISeulement,
                    this.centre, this.dateDeb, this.dateFin, this.retour, ratio, ratioComp );
            if (this.centre != null)
                aff.deleteCentre(this.centre);
            aff.run();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(fenetre,"Problème de calcul du ratio pour cette date.");
            Bureau bureau = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
            bureau.run();
        }
    }
}
