package fr.iutfbleau.projetIHM2021FI2.IHM2.Controller;

import java.awt.event.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;

public class TraitementGraphique implements ActionListener {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JPanel centre;

    /**
     * 
     * @param fenetre
     * @param grandLivreDOrAPISeulement
     * @param centre
     */
    public TraitementGraphique(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, JPanel centre) {
        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.centre = centre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Bouton Graphique.");
    }
}
