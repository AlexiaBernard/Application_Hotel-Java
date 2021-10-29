package fr.iutfbleau.projetIHM2021FI2.IHM2.Controller;

import java.awt.event.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.IHM2.Model.*;

public class TraitementGraphique implements ActionListener {

    private JFrame fenetre;
    private BD baseDeDonnées;

    /**
     * 
     * @param fenetre
     * @param baseDeDonnées
     */
    public TraitementGraphique(JFrame fenetre, BD baseDeDonnées) {
        this.fenetre = fenetre;
        this.baseDeDonnées = baseDeDonnées;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Bouton Graphique.");
        
    }
    
}
