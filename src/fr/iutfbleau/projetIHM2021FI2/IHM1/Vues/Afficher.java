package fr.iutfbleau.projetIHM2021FI2.IHM1.Vues;
import java.awt.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Controller.*;


public class Afficher {

    public Afficher(JFrame fenetre, Chambre ch1, Prereservation prereservation){

        JPanel affichage = new JPanel();

        JLabel num_chambre = new JLabel("La chambre numéro "+ch1.getNumero()+" est disponible.");
       
        //boutons
        JPanel boutons = new JPanel();
        JButton valider = new JButton("Valider"); //à changer la taille de ce bouton
        JButton liste = new JButton("Demander la liste"); //à changer la taille de ce bouton

        boutons.setLayout(new GridLayout(1,2));
		boutons.add(valider);
		boutons.add(liste);

        //Ajout au Panel affichage
        affichage.setLayout(new GridLayout(2,1));
		affichage.add(num_chambre);
		affichage.add(boutons);

        //Ajout à la fenetre
        fenetre.add(affichage, BorderLayout.CENTER);

        valider.addActionListener(new TraitementValider(fenetre, ch1, prereservation));
        liste.addActionListener(new TraitementListe(fenetre));

        fenetre.setVisible(true);
        
    }
    
}
