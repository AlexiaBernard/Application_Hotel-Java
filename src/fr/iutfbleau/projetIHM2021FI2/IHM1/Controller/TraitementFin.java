package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import javax.swing.*;
import java.sql.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;

/**
 * <code>TraitementFin</code> est un contrôleur.
 * Il gère les options fin et annuler lors de la réservation de chambre.
 * De plus en cas de problème, il permet de fermer la fenêtre 
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class TraitementFin {

    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;

    /**
     * Récupère la fenêtre pour la gérer
     * @param grandLivreDOrAPISeulement
     * @param fenetre la fenêtre 
     */
    public TraitementFin(ReservationFactory grandLivreDOrAPISeulement,JFrame fenetre) {
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
    }

    /**
     * Permet de fermer la fenetre
     */
    public void run(){
        Connection connexion = this.grandLivreDOrAPISeulement.getConnexion();
        if (connexion!=null){
            try {
                connexion.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this.fenetre,"Problème de fermeture de la base de données.");
            }
        }
        this.fenetre.dispose();
    }
}
