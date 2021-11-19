package fr.iutfbleau.projetIHM2021FI2.IHM1.View;

import java.util.*;
import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Controller.*;

/**
 * <code>AfficherReservations</code> est une vue
 * Elle affiche la liste des réservations faites par une personne qui a donné son nom et prénom
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class AfficherReservations{

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private Set<Prereservation> prereservations;
    private JPanel centre;

    /**
     * Constructeur permettant d'accéder à la liste des réservations effectuées par une personne
     * @param bookingPointComAPISeulement interface PreservationFactory de l'API correspondant à la préreservation
     * @param grandLivreDOrAPISeulement modèle non persistant de Réservation
     * @param fenetre la fenetre
     * @param centre centre de la fenetre
     * @param prereservation la préreservation 
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
    
    /**
     * Affiche la liste des réservations au centre de la fenetre sous forme de liste
     * Il est possible d'en choisir une grâce à des boutons-radio
     */
    public void run(){
        Color fond = new Color(222,212,249);
        int compteur = 0;
        JPanel panel_liste = new JPanel();
        ButtonGroup group = new ButtonGroup();
         //Panel du boutton pour pouvoir définir sa taille
         JPanel valider_jp = new JPanel();
         valider_jp.setBackground(fond);
         JButton valider = new JButton("Valider");
         valider.setPreferredSize(new Dimension(100, 30));
         valider_jp.add(valider);

        for(Prereservation p : this.prereservations){
            compteur++;
        }

        panel_liste.setLayout(new GridLayout(4,compteur%4));
        panel_liste.setBackground(fond);
        compteur = 0;
        for(Prereservation p : this.prereservations){
           JRadioButton btn1 = new JRadioButton();
           btn1.setBackground(fond);
           btn1.setText(""+p.getReference());
           btn1.setActionCommand(""+p.getReference());
           group.add(btn1);
           panel_liste.add(btn1);
        }

        this.centre.setLayout(new GridLayout(2,1));
        this.centre.setBackground(fond);
        this.centre.add(panel_liste);
        this.centre.add(valider_jp);

        this.fenetre.add(this.centre, BorderLayout.CENTER);
        this.fenetre.setVisible(true);

        valider.addActionListener(new TraitementListeValiderReference(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, this.fenetre, group, this.prereservations, this.centre));
    }

    /**
     * Enlève les informations affichées au centre
     * @param centre centre de la fenêtre
     */
    public void deleteRef(JPanel centre){
        this.fenetre.remove(centre);
        this.fenetre.repaint();
        this.fenetre.revalidate();
        this.fenetre.setVisible(true);
    }
}
