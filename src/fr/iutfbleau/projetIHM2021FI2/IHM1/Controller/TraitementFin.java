package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import javax.swing.*;

/**
 * <code>TraitementFin</code> est un contrôleur.
 * Il gère les options fin et annuler lors de la réservation de chambre.
 * De plus en cas de problème, il permet de fermer la fenêtre 
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class TraitementFin {

    private JFrame fenetre;

    /**
     * Récupère la fenêtre pour la gérer
     * @param fenetre la fenêtre 
     */
    public TraitementFin(JFrame fenetre) {
        this.fenetre = fenetre;
    }

    /**
     * Permet de fermer la fenetre
     */
    public void run(){
        this.fenetre.dispose();
    }
}
