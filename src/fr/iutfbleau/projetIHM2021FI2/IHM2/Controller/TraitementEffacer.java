package fr.iutfbleau.projetIHM2021FI2.IHM2.Controller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.Bureau;

public class TraitementEffacer implements ActionListener {

    private JFrame fenetre;
    private JPanel taux_p;
    private JPanel graphique_p;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JPanel centre;

    /**
     * 
     * @param fenetre
     * @param graphique_p
     * @param taux_p
     * @param grandLivreDOrAPISeulement
     * @param centre
     */
    public TraitementEffacer(JFrame fenetre, JPanel taux_p, JPanel graphique_p, 
            ReservationFactory grandLivreDOrAPISeulement, JPanel centre) {
        this.fenetre = fenetre;
        this.taux_p = taux_p;
        this.graphique_p = graphique_p;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.centre = centre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.fenetre.removeAll();
        this.fenetre.add(this.taux_p, BorderLayout.NORTH);
        this.fenetre.add(this.centre, BorderLayout.CENTER);
        this.fenetre.add(this.graphique_p, BorderLayout.SOUTH);
        this.fenetre.repaint();
        this.fenetre.revalidate();
        /*if (this.centre == null){
            this.fenetre.removeAll();
        } else {
            this.centre.removeAll();
            this.fenetre.repaint();
            this.fenetre.revalidate();
            this.fenetre.setVisible(true);
        }*/
        Bureau bur = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
        bur.run();
    }
}
