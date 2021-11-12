package fr.iutfbleau.projetIHM2021FI2.IHM2.View;

import java.time.*;
import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;

public class AfficherRatioType {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private LocalDate date;
    private int ratio;
    private TypeChambre type;
    private JPanel centre;

    /**
     * 
     * @param fenetre
     * @param grandLivreDOrAPISeulement
     * @param date
     * @param ratio
     * @param type
     * @param centre
     */
    public AfficherRatioType(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, LocalDate date, int ratio, TypeChambre type, JPanel centre) {
        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.date = date;
        this.ratio = ratio; 
        this.type = type;
        this.centre = centre;
    }
    
    public void run(){
        String type = "";
        if (this.type == TypeChambre.UNLD){
            type = "un lit double";
        } else if (this.type == TypeChambre.UNLS){
            type = "un lit simple";
        } else if (this.type == TypeChambre.DEUXLS){
            type = "deux lits simples";
        }
        JLabel ratio = new JLabel("Le ratio pour le "+this.date+" et pour le type "+type+" est de "+this.ratio+".");

        this.centre.add(ratio);

        this.fenetre.add(centre, BorderLayout.CENTER);
        this.fenetre.setVisible(true);
    }
}