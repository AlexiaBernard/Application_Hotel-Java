package fr.iutfbleau.projetIHM2021FI2.IHM2.View;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.IHM2.Model.BD;

public class Main {
    
    public static void main(String[] args) {

        JFrame fenetre = new JFrame("IHM2");

        fenetre.setSize(500, 500);
	    fenetre.setLocation(100, 100);
	    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                      
        JPanel centre = new JPanel();
        
        BD baseDeDonnées = new BD();
        ReservationFactory grandLivreDOrAPISeulement = baseDeDonnées.Connexion(fenetre);
        JOptionPane.showMessageDialog(fenetre,"La Base de données est prête.");
        Bureau bureau = new Bureau(fenetre, grandLivreDOrAPISeulement, centre);
        bureau.run();
    }
}
