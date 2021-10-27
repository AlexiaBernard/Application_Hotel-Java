package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import javax.swing.*;

/**
 * <code>TraitementFin</code> est un contrôleur.
 * Il gère le bouton ...
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class TraitementFin {

    private JFrame fenetre;

    public TraitementFin(JFrame fenetre) {
        this.fenetre = fenetre;
    }

    public void run(){
        this.fenetre.dispose();
    }
}
