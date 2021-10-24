package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import javax.swing.*;

public class TraitementFin {

    private JFrame fenetre;

    public TraitementFin(JFrame fenetre) {
        this.fenetre = fenetre;
    }

    public void run(){
        this.fenetre.dispose();
    }
}
