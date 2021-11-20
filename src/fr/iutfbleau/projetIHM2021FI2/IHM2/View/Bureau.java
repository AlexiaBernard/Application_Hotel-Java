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
        Color fond = new Color(222,212,249);

    //------------------Partie du haut : Les taux d'occupation--------------//
        //Taux d'occupation
        JLabel taux_l = new JLabel("Taux d'occupation d'une journée");
        taux_l.setHorizontalAlignment(SwingConstants.CENTER);

        //Panel du boutton pour pouvoir définir sa taille
        JButton taux_date = new JButton("Demander");
        JPanel taux_d_p = new JPanel();
        taux_date.setPreferredSize(new Dimension(250, 30));
        taux_d_p.setBackground(fond);
        taux_d_p.add(taux_date);

        //Panel du boutton pour pouvoir définir sa taille
        JButton taux_type = new JButton("Demander par type");
        JPanel taux_t_p = new JPanel();
        taux_type.setPreferredSize(new Dimension(250, 30));
        taux_t_p.setBackground(fond);
        taux_t_p.add(taux_type);

        //JPanel des btns 
        JPanel btn_p = new JPanel();
        btn_p.setBackground(fond);
        btn_p.setLayout(new GridLayout(1,2));
        btn_p.add(taux_d_p);
        btn_p.add(taux_t_p);

        //JPanel du taux
        JPanel taux_p = new JPanel();
        taux_p.setBackground(fond);
        taux_p.setName("taux");
        taux_p.setLayout(new GridLayout(2,1));
        taux_p.add(taux_l);
        taux_p.add(btn_p);

    //------------Partie du bas : Le graphique et le bouton effacer------------//
        //Bouton effacer
        JButton eff = new JButton("Effacer");
        //Panel du boutton "Effacer" pour pouvoir définir sa taille
        JPanel eff_p = new JPanel();
        eff.setPreferredSize(new Dimension(100, 30));
        eff_p.setBackground(fond);
        eff_p.add(eff);
        /* 
        Problème avec le bouton effacer : Il fontionne pour effacer le centre, c'est à dire lorsque
        l'on y met les taux, le bouton les efface. Cependant lorsque c'est le graphique il l'efface
        uniquement si on ne fait pas d'autre action entre l'afichage du graphique et la demander pour
        effacer.
        Possible solution :  
            _essayer de mettre le graphique dans le JPanel centre, afin de pouvoir par la suite l'effacer
        */
        
        //Label du graphique
        JLabel graphique_l = new JLabel("Graphique d'occupation");
        graphique_l.setHorizontalAlignment(SwingConstants.CENTER);
        //Panel du boutton "Demander" pour pouvoir définir sa taille
        JButton graphique = new JButton("Demander");
        JPanel graph_p = new JPanel();
        eff.setPreferredSize(new Dimension(100, 30));
        graph_p.setBackground(fond);
        graph_p.add(graphique);
        

        //Jpanel du Label et du bouton 
        JPanel panel = new JPanel();
        panel.setBackground(fond);
        panel.setLayout(new GridLayout(1,2));
        panel.add(graphique_l);
        panel.add(graph_p);

        //Panel de la partie graphique entière
        JPanel graphique_p = new JPanel();
        graphique_p.setBackground(fond);
        graphique_p.setName("graph");
        graphique_p.setLayout(new GridLayout(2,1));
        graphique_p.add(eff_p);
        graphique_p.add(panel);

        this.centre.setName("centre");
        this.centre.setBackground(fond);

        this.fenetre.getContentPane().setBackground(fond);

        this.fenetre.add(taux_p, BorderLayout.NORTH);
        this.fenetre.add(this.centre, BorderLayout.CENTER);
        this.fenetre.add(graphique_p, BorderLayout.SOUTH);

        //Listener
        eff.addActionListener(new TraitementEffacer(this.fenetre, this.grandLivreDOrAPISeulement, this.centre));
        taux_date.addActionListener(new TraitementTaux(this.fenetre, this.grandLivreDOrAPISeulement, this.centre));
        taux_type.addActionListener(new TraitementTauxType(this.fenetre, this.grandLivreDOrAPISeulement, this.centre));
        graphique.addActionListener(new TraitementGraphique(this.fenetre, this.grandLivreDOrAPISeulement, this.centre));
        
        this.fenetre.setVisible(true);
    }    
}
