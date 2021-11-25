package fr.iutfbleau.projetIHM2021FI2.IHM1.View;

import java.util.*;
import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Controller.*;

/**
 * <code>AfficherListe</code> est une vue
 * Elle affiche la liste des chambres libres de la même catégorie que la chambre à changer
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class AfficherListe {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private Prereservation prereservation;
    private Set<Chambre> disponibles;

    /**
     * Constructeur permettant d'accéder à la liste des chambres libres par la suite
     * 
     * @param bookingPointComAPISeulement interface PreservationFactory de l'API
     *                                    correspondant à la préreservation
     * @param grandLivreDOrAPISeulement   modèle non persistant de Réservation
     * @param fenetre                     la fenetre
     * @param prereservation              la préreservation
     * @param disponibles                 chambres disponibles
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

    /**
     * Affiche la liste de chambres disponibles au centre de la fenetre sous forme
     * de liste. Il est possible d'en choisir une grâce à des boutons-radio et un
     * bouton Valider
     */
    public void run(){
        Color fond = new Color(222,212,249);
        int compteur = 0;
        JPanel panel_liste1 = new JPanel();
        ButtonGroup group = new ButtonGroup();
        //Panel du boutton pour pouvoir définir sa taille
        JPanel valider_jp = new JPanel();
        valider_jp.setBackground(fond);
        JButton valider = new JButton("Valider");
        valider.setPreferredSize(new Dimension(100, 30));
        valider_jp.add(valider);


        for(Chambre c : this.disponibles){
            compteur++;
        }

        panel_liste1.setLayout(new GridLayout(4,compteur%4));
        panel_liste1.setBackground(fond);
        compteur = 0;
        for(Chambre c : this.disponibles){
           JRadioButton btn = new JRadioButton();
           btn.setBackground(fond);
           btn.setText(""+c.getNumero());
           btn.setActionCommand(""+c.getNumero());
           group.add(btn);
           panel_liste1.add(btn);
        }

        JPanel centre = new JPanel();
        centre.setLayout(new GridLayout(2,1));
        centre.setBackground(fond);
        centre.add(panel_liste1);
        centre.add(valider_jp);

        this.fenetre.add(centre, BorderLayout.CENTER);
        this.fenetre.setVisible(true);

        valider.addActionListener(new TraitementListeValider(this.bookingPointComAPISeulement, 
                this.grandLivreDOrAPISeulement ,this.fenetre, prereservation,group, 
                this.disponibles, centre));
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
