package fr.iutfbleau.projetIHM2021FI2.IHM2.Model;

import java.time.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.AfficherGraphique;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.Bureau;

/**
 * <code>VerificationDateGraphique</code> est un modèle.
 * Il sert à calculer le ratio sur une période donnée pour faire le graphique.
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class VerificationDateGraphique {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private LocalDate dateFin;
    private LocalDate dateDeb;
    private int retour;
    private JPanel centre;

    /**
     * Constructeur permettant de récupérer les informations nécessaires à la
     * création du graphique
     * 
     * @param fenetre                   la fenetre
     * @param grandLivreDOrAPISeulement modèle non persistant de Réservation
     * @param dateDeb                   date de début
     * @param dateFin                   date de fin
     * @param retour                    retour
     * @param centre                    centre de la fenêtre
     */
    public VerificationDateGraphique(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement,
        LocalDate dateFin,LocalDate dateDeb, int retour, JPanel centre) {
        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.dateFin = dateFin;
        this.dateDeb = dateDeb;
        this.retour = retour;
        this.centre = centre;
    }

    /**
     * Vérification de l'état des dates données et appel de la fonction pour
     * l'affichage du graphique
     */
    public void run() {
        try {
            int ratio = grandLivreDOrAPISeulement.getRatio(this.dateDeb,this.dateFin);
            int ratioComp = grandLivreDOrAPISeulement.getRatio(this.dateFin.minusYears(3),this.dateFin);
            AfficherGraphique aff = new AfficherGraphique(this.fenetre, this.centre, this.retour, ratio, ratioComp );
            aff.run();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(fenetre,"Problème de calcul du ratio pour ces dates.");
            Bureau bureau = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
            bureau.run();
        }
    }
}
