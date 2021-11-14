package fr.iutfbleau.projetIHM2021FI2.IHM2.Controller;

import java.awt.event.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;

public class TraitementEffacer implements ActionListener {

    public JFrame fenetre;
    public ReservationFactory grandlivreDOrAPISeulement;
    public JPanel centre;

    /**
     * 
     * @param fenetre
     * @param grandLivreDOrAPISeulement
     * @param centre
     */
    public TraitementEffacer(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, 
            JPanel centre) {
        this.fenetre = fenetre;
        this.grandlivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.centre = centre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Effacer");
        if (this.centre == null){
            JOptionPane.showMessageDialog(this.fenetre,"Il n'y a rien a effacer.");
        } else {
            this.centre.removeAll();
            this.fenetre.repaint();
            this.fenetre.revalidate();
            this.fenetre.setVisible(true);
        }
    }
}
