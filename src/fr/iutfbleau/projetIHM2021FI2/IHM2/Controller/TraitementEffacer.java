package fr.iutfbleau.projetIHM2021FI2.IHM2.Controller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.Bureau;

/**
 * <code>TraitementEffacer</code> est un contrôleur. Il gère le bouton effacer
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class TraitementEffacer implements ActionListener {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JPanel centre;

    /**
     * Constructeur qui permer d'accéder à la fenêtre par la suite
     * 
     * @param fenetre                   la fenetre
     * @param grandLivreDOrAPISeulement modèle non persistant de Réservation
     * @param centre                    le centre de la fenetre
     */
    public TraitementEffacer(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, JPanel centre) {
        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.centre = centre;
    }

    /**
     * Efface les composants de la fenetre
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Component comp[] = this.fenetre.getContentPane().getComponents();
        boolean verif = false;
        for (int i = 0; i < comp.length; i++) {
            if (comp[i].equals(this.centre)) {
                verif = true;
                break;
            }
        }
        if (verif == false) {
            int val = -1;
            for (int i = 0; i < comp.length; i++) {
                if (comp[i].getName().equals("graphique")) {
                    val = i;
                    break;
                }
            }
            this.fenetre.remove(comp[val]);
        } else {
            this.centre.removeAll();
        }
        this.fenetre.repaint();
        this.fenetre.revalidate();
        this.fenetre.setVisible(true);
        Bureau bur = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
        bur.run();
    }
}
