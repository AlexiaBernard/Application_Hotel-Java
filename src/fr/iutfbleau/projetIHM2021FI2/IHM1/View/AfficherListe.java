package fr.iutfbleau.projetIHM2021FI2.IHM1.View;

import java.util.*;
import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Controller.*;

public class AfficherListe {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private Prereservation prereservation;
    private Set<Chambre> disponibles;

    /**
     * 
     * @param bookingPointComAPISeulement
     * @param grandLivreDOrAPISeulement
     * @param fenetre
     * @param prereservation
     * @param disponibles
     */
    public AfficherListe(PrereservationFactory bookingPointComAPISeulement, 
            ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, 
            Prereservation prereservation, Set<Chambre> disponibles){
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.prereservation = prereservation;
        this.disponibles = disponibles;
    }    

    public void run(){
        int compteur = 0;
        JPanel panel_liste1 = new JPanel();
        ButtonGroup group = new ButtonGroup();
        JButton valider = new JButton("Valider");

        for(Chambre c : this.disponibles){
            compteur++;
        }

        panel_liste1.setLayout(new GridLayout(4,compteur%4));
        compteur = 0;
        for(Chambre c : this.disponibles){
           JRadioButton btn = new JRadioButton();
           btn.setText(""+c.getNumero());
           btn.setActionCommand(""+c.getNumero());
           group.add(btn);
           panel_liste1.add(btn);
        }

        JPanel centre = new JPanel();
        centre.setLayout(new GridLayout(2,1));
        centre.add(panel_liste1);
        centre.add(valider);

        this.fenetre.add(centre, BorderLayout.CENTER);
        this.fenetre.setVisible(true);

        valider.addActionListener(new TraitementListeValider(this.bookingPointComAPISeulement, 
                this.grandLivreDOrAPISeulement ,this.fenetre, prereservation,group, 
                this.disponibles, centre));
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