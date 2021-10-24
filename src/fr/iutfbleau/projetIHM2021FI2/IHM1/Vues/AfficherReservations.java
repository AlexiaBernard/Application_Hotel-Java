package fr.iutfbleau.projetIHM2021FI2.IHM1.Vues;

import java.util.*;
import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Controller.*;

public class AfficherReservations {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private Set<Prereservation> prereservations;
    JPanel centre;

    /**
     * 
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement
     * @param fenetre
     * @param prereservations
     */
    public AfficherReservations(PrereservationFactory bookingPointComAPISeulement,
            ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, Set<Prereservation> prereservations) {
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.prereservations = prereservations;
        this.centre = new JPanel();
    }
    
    public void run(){
        int compteur = 0;
        JPanel panel_liste = new JPanel();
        ButtonGroup group = new ButtonGroup();
        JButton valider = new JButton("Valider");

        for(Prereservation p : this.prereservations){
            compteur++;
        }

        panel_liste.setLayout(new GridLayout(4,compteur%4));
        compteur = 0;
        for(Prereservation p : this.prereservations){
           JRadioButton btn = new JRadioButton();
           btn.setText(""+p.getReference());
           btn.setActionCommand(""+p.getReference());
           group.add(btn);
           panel_liste.add(btn);
        }

        this.centre.setLayout(new GridLayout(2,1));
        this.centre.add(panel_liste);
        this.centre.add(valider);

        this.fenetre.add(this.centre, BorderLayout.CENTER);
        this.fenetre.setVisible(true);

        valider.addActionListener(new TraitementListeValiderReference(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement ,this.fenetre, group, this.prereservations));
    }


    //Je n'arrive pas à enlever ce qu'il y avait au centre précédemment (les perreservations) 
    //pour afficher maintenant les chambres :/
    //Cette fonction ne provoque aucune erreur mais ne fonctionne pas :/
    public void deleteRef(){
        this.fenetre.remove(this.centre);
        this.fenetre.repaint();
    }
}
