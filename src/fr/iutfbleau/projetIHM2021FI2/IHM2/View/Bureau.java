package fr.iutfbleau.projetIHM2021FI2.IHM2.View;

import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2021FI2.IHM2.Controller.*;
import fr.iutfbleau.projetIHM2021FI2.IHM2.Model.*;

public class Bureau {

    private JFrame fenetre;
    private BD baseDeDonnées;

    /**
     * 
     * @param fenetre
     * @param baseDeDonnées
     */
    public Bureau(JFrame fenetre, BD baseDeDonnées){
        this.fenetre = fenetre;
        this.baseDeDonnées = baseDeDonnées;
    }

    public void run() {

        JPanel panel = new JPanel();
        JLabel vide = new JLabel("");

        //Taux d'occupation
        JPanel taux_p = new JPanel();
        JLabel taux_l = new JLabel("Taux d'occupation");
        JButton taux = new JButton("Demander");
        taux_p.setLayout(new GridLayout(2,1));
        taux_p.add(taux_l);
        taux_p.add(taux);


        //Graphique d'occupation
        JPanel graphique_p = new JPanel();
        JLabel graphique_l = new JLabel("Graphique d'occupation");
        JButton graphique = new JButton("Demander");
        graphique_p.setLayout(new GridLayout(2,1));
        graphique_p.add(graphique_l);
        graphique_p.add(graphique);

        panel.setLayout(new GridLayout(3,1));
        panel.add(taux_p);
        panel.add(vide);
        panel.add(graphique_p);

        this.fenetre.add(panel, BorderLayout.CENTER);

        this.fenetre.setVisible(true);

        //Listener
        taux.addActionListener(new TraitementTaux(this.fenetre, this.baseDeDonnées));
        graphique.addActionListener(new TraitementGraphique(this.fenetre, this.baseDeDonnées) );
        
    }    
}
