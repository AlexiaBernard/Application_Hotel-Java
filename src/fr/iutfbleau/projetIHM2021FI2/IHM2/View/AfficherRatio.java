package fr.iutfbleau.projetIHM2021FI2.IHM2.View;

import java.time.*;
import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;

/**
 * <code>AfficherRatio</code> est une vue
 * Elle affiche le ratio de l'hotel pour une date donnée.
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class AfficherRatio {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private LocalDate date;
    private int ratio;
    private JPanel centre;

    /**
     * Constructeur permettant de récupérer les informations nécessaires à
     * l'affichage du ratio
     * 
     * @param fenetre                   la fenetre
     * @param grandLivreDOrAPISeulement modèle non persistant de Réservation
     * @param date                      date donnée
     * @param ratio                     le ratio
     * @param centre                    centre de la fenetre
     */
    public AfficherRatio(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, LocalDate date, int ratio,
            JPanel centre) {
        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.date = date;
        this.ratio = ratio;
        this.centre = centre;
    }

    /**
     * Affichage du ration d'une date donnée
     */
    public void run() {
        JLabel ratio = new JLabel("Le ratio pour le " + this.date + " est de " + this.ratio + ".");

        this.centre.add(ratio);
        this.fenetre.add(centre, BorderLayout.CENTER);
        this.fenetre.setVisible(true);
    }

    /**
     * Effaçage du centre de la fenêtre
     */
    public void deleteCentre() {
        this.fenetre.remove(this.centre);
        this.fenetre.repaint();
        this.fenetre.revalidate();
        this.fenetre.setVisible(true);
    }
}
