package fr.iutfbleau.projetIHM2021FI2.IHM2.View;

import java.time.*;
import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;

/**
 * <code>AfficherRatioType</code> est une vue
 * Elle affiche le ratio de l'hotel pour une date et un type de chambre donnés.
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class AfficherRatioType {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private LocalDate date;
    private int ratio;
    private TypeChambre type;
    private JPanel centre;

    /**
     * Constructeur permettant de récupérer les informations nécessaires à
     * l'affichage du ratio
     * 
     * @param fenetre                   fenêtre
     * @param grandLivreDOrAPISeulement modèle non persistant de Réservation
     * @param date                      date donnée
     * @param ratio                     ratio de la date
     * @param type                      type de chambre
     * @param centre                    centre de la fenêtre
     */
    public AfficherRatioType(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, LocalDate date, int ratio,
            TypeChambre type, JPanel centre) {
        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.date = date;
        this.ratio = ratio;
        this.type = type;
        this.centre = centre;
    }

    /**
     * Affichage du type de chambre choisi et du ratio d'une date donnée pour le type de chambre
     */
    public void run() {
        String type = "";
        if (this.type == TypeChambre.UNLD) {
            type = "un lit double";
        } else if (this.type == TypeChambre.UNLS) {
            type = "un lit simple";
        } else if (this.type == TypeChambre.DEUXLS) {
            type = "deux lits simples";
        }
        JLabel ratio = new JLabel(
                "Le ratio pour le " + this.date + " et pour le type " + type + " est de " + this.ratio + ".");

        this.centre.add(ratio);

        this.fenetre.add(centre, BorderLayout.CENTER);
        this.fenetre.setVisible(true);
    }
}