package fr.iutfbleau.projetIHM2021FI2.IHM2.View;

import java.time.*;
import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;

/**
 * <code>AfficherFin</code> est une vue
 * Elle affiche le ratio de l'hotel pour une date donnée.
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class AfficherRatio {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private LocalDate date;
    private int ratio;
    private JPanel centre;

    /**
     * 
     * @param fenetre
     * @param grandLivreDOrAPISeulement
     * @param date
     * @param ratio
     * @param centre
     */
    public AfficherRatio(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, LocalDate date, int ratio, JPanel centre) {
        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.date = date;
        this.ratio = ratio; 
        this.centre = centre;
    }
    
    public void run(){
        JLabel ratio = new JLabel("Le ratio pour le "+this.date+" est de "+this.ratio+".");

        this.centre.add(ratio);
        this.fenetre.add(centre, BorderLayout.CENTER);
        this.fenetre.setVisible(true);
    }

    public void deleteCentre(){
        this.fenetre.remove(this.centre);
        this.fenetre.repaint();
        this.fenetre.revalidate();
        this.fenetre.setVisible(true);
    }
}
