package fr.iutfbleau.projetIHM2021FI2.IHM2.Model;

import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.API.TypeChambre;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.AfficherRatio;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.Bureau;

public class VerificationDateType {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private LocalDate date;
    private TypeChambre type;
    private JPanel centre;

    /**
     * 
     * @param fenetre
     * @param grandLivreDOrAPISeulement
     * @param date
     * @param type
     * @param centre
     */
    public VerificationDateType(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, LocalDate date,
            TypeChambre type, JPanel centre) {

        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.date = date;
        this.type = type;
        this.centre = centre;

    }

    public void run(){
        try {
            int ratio = grandLivreDOrAPISeulement.getRatio(this.date, this.type);
            AfficherRatio aff = new AfficherRatio(this.fenetre, this.grandLivreDOrAPISeulement, this.date, ratio, this.centre);
            if(this.centre != null)
                aff.deleteCentre();
            aff.run();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(fenetre,"Problème de calcul du ratio pour cette date et ce type de chambre.");
            Bureau bureau = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
            bureau.run();
        }
    }
    
}
