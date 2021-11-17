package fr.iutfbleau.projetIHM2021FI2.IHM2.View;

import java.time.LocalDate;
import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;

public class AfficherGraphique  {

    private JFrame fenetre;
    private JPanel centre;
    private int retour;
    private int ratio;
    private int ratioComp;

    /**
     * 
     * @param fenetre
     * @param centre
     * @param retour
     * @param ratio
     * @param ratioComp
     */
    public AfficherGraphique(JFrame fenetre, JPanel centre, int retour, int ratio, int ratioComp) {
        this.fenetre = fenetre;
        this.centre = centre;
        this.retour = retour;
        this.ratio = ratio;
        this.ratioComp = ratioComp;        
    }

    public void run() {
        DessinerGraphique graphique = new DessinerGraphique(this.fenetre, this.retour, 
                this.ratio, this.ratioComp);
        this.fenetre.remove(this.centre);
        graphique.setName("graphique");
        this.fenetre.add(graphique, BorderLayout.CENTER);
        this.fenetre.repaint();
        this.fenetre.revalidate();
        this.fenetre.setVisible(true);
    }
}
