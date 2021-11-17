package fr.iutfbleau.projetIHM2021FI2.IHM2.Controller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.Bureau;

public class TraitementEffacer implements ActionListener {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JPanel centre;

    /**
     * 
     * @param fenetre
     * @param grandLivreDOrAPISeulement
     * @param centre
     */
    public TraitementEffacer(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, 
            JPanel centre) {
        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.centre = centre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component comp [] =this.fenetre.getContentPane().getComponents();
        boolean verif = false;
        for (int i=0; i< comp.length; i++){
            if (comp[i].equals(this.centre)){
                verif = true;
                break;
            }
        }
        if ( verif == false){
            JOptionPane.showMessageDialog(this.fenetre,"Il n'y a rien a effacer.");
        } else {
            this.centre.removeAll();
            this.fenetre.repaint();
            this.fenetre.revalidate();
            this.fenetre.setVisible(true);
        }
        Bureau bur = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
        bur.run();
    }
}
