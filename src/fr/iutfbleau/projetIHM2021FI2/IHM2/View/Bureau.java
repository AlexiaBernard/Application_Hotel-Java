package fr.iutfbleau.projetIHM2021FI2.IHM2.View;

import java.awt.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.IHM2.Controller.*;

public class Bureau {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JPanel centre;

    /**
     * 
     * @param fenetre
     * @param grandLivreDOrAPISeulement
     * @param centre
     */
    public Bureau(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, JPanel centre){
        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.centre = centre;
    }

    public void run() {

        //Bouton effacer
        JButton eff = new JButton("Effacer");

        //Taux d'occupation
        JPanel taux_p = new JPanel();
        JLabel taux_l = new JLabel("Taux d'occupation d'une journée");
        JButton taux_date = new JButton("Demander");
        JButton taux_type = new JButton("Demander par type");
        taux_p.setLayout(new GridLayout(3,1));
        taux_p.add(taux_l);
        taux_p.add(taux_date);
        taux_p.add(taux_type);


        //Graphique d'occupation
        JPanel graphique_p = new JPanel();
        JLabel graphique_l = new JLabel("Graphique d'occupation");
        JButton graphique = new JButton("Demander");
        graphique_p.setLayout(new GridLayout(3,1));
        graphique_p.add(eff);
        graphique_p.add(graphique_l);
        graphique_p.add(graphique);

        this.fenetre.add(taux_p, BorderLayout.NORTH);
        this.fenetre.add(this.centre, BorderLayout.CENTER);
        this.fenetre.add(graphique_p, BorderLayout.SOUTH);

        this.fenetre.setVisible(true);

        //Listener
        eff.addActionListener(new TraitementEffacer(this.fenetre, this.grandLivreDOrAPISeulement, this.centre));
        taux_date.addActionListener(new TraitementTaux(this.fenetre, this.grandLivreDOrAPISeulement, this.centre));
        taux_type.addActionListener(new TraitementTauxType(this.fenetre, this.grandLivreDOrAPISeulement, this.centre));
        graphique.addActionListener(new TraitementGraphique(this.fenetre, this.grandLivreDOrAPISeulement, this.centre));
        
    }    
}
