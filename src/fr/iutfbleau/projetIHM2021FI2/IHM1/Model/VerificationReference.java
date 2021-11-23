package fr.iutfbleau.projetIHM2021FI2.IHM1.Model;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.View.*;

import javax.swing.*;

/**
 * <code>AjoutReservation</code> est un modèle.
 * Il sert à vérifier la correspondance entre une référence et une préréservation dans la base de données.
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class VerificationReference {

    private PrereservationFactory bookingPointComAPISeulement;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JFrame fenetre;
    private String reference;
    private JPanel centre;

    /**
     * Constructeur permettant d'accéder aux informations sur la référence
     * @param bookingPointComAPISeulement interface PreservationFactory de l'API correspondant à la préreservation
     * @param grandLivreDOrAPISeulement modèle non persistant de Réservation
     * @param fenetre la fenetre
     * @param reference référence de la préréservation
     * @param prenom le prénom du client
     */
    public VerificationReference(PrereservationFactory bookingPointComAPISeulement,
            ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, String reference, JPanel centre) {
        this.bookingPointComAPISeulement = bookingPointComAPISeulement;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.fenetre = fenetre;
        this.reference = reference;
        this.centre = centre;
    }

    /**
     * Vérification dans la BD si la référence existe
     */
    public void run() {
        try {
            Prereservation prereservation = this.bookingPointComAPISeulement.getPrereservation(this.reference);
            Chambre libre = null;
            try {
                libre = this.grandLivreDOrAPISeulement.getChambre(prereservation);
                Afficher afficher = new Afficher(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement,
                        this.fenetre, libre, prereservation, this.centre);
                afficher.run();
            } catch (IllegalStateException e) {
                JOptionPane.showMessageDialog(this.fenetre, "Problème pour récupérer la préréservation.");
                Menu menu = new Menu(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, this.fenetre,
                        this.centre);
                menu.run();
            }
        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(this.fenetre, "Pas de préreservation trouvée avec cette référence.");
            Menu menu = new Menu(this.bookingPointComAPISeulement, this.grandLivreDOrAPISeulement, this.fenetre,
                    this.centre);
            menu.run();
        }
    }
}
