package fr.iutfbleau.projetIHM2021FI2.IHM1.View;

import java.util.*;
import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Controller.*;

public class AfficherReservations{

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private Set<Prereservation> prereservations;
    private JPanel centre;

    /**
     * 
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement
     * @param fenetre
     * @param centre
     * @param prereservations
     */
    public AfficherReservations(PrereservationFactory bookingPointComAPISeulement,
            ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, 
            JPanel centre, Set<Prereservation> prereservations) {
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.centre = centre;
        this.prereservations = prereservations;
    }
    
    public void run(){
        int compteur = 0;
        JPanel panel_liste = new JPanel();
        ButtonGroup group = new ButtonGroup();
        JButton valider1 = new JButton("Valider");

        for(Prereservation p : this.prereservations){
            compteur++;
        }

        panel_liste.setLayout(new GridLayout(4,compteur%4));
        compteur = 0;
        for(Prereservation p : this.prereservations){
           JRadioButton btn1 = new JRadioButton();
           btn1.setText(""+p.getReference());
           btn1.setActionCommand(""+p.getReference());
           group.add(btn1);
           panel_liste.add(btn1);
        }

        this.centre.setLayout(new GridLayout(2,1));
        this.centre.add(panel_liste);
        this.centre.add(valider1);

        this.fenetre.add(this.centre, BorderLayout.CENTER);
        this.fenetre.setVisible(true);

        valider1.addActionListener(new TraitementListeValiderReference(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, this.fenetre, group, this.prereservations, this.centre));
    }

    /**
     * 
     * @param centre
     */
    public void deleteRef(JPanel centre){
        this.fenetre.remove(centre);
        this.fenetre.repaint();
        this.fenetre.revalidate();
        this.fenetre.setVisible(true);
    }
}
