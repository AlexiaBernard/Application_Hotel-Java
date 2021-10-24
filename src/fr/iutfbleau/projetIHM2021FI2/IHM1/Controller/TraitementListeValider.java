package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import java.awt.event.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;

public class TraitementListeValider implements ActionListener {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private Prereservation prereservation;
    private ButtonGroup group;

    /**
     * 
     * @param bookingPointComAPISeulement2
     * @param grandLivreDOrAPISeulement2
     * @param fenetre2
     * @param prereservation2
     * @param group
     */
    public TraitementListeValider(PrereservationFactory bookingPointComAPISeulement2,
            ReservationFactory grandLivreDOrAPISeulement2, JFrame fenetre2, Prereservation prereservation2,
            ButtonGroup group) {
                this.bookingPointComAPISeulement = bookingPointComAPISeulement;
                this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
                this.fenetre = fenetre;
                this.group = group;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Bouton : "+group.getSelection().getActionCommand()+" selectionne.\n");
    }

}
