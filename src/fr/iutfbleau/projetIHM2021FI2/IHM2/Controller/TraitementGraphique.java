package fr.iutfbleau.projetIHM2021FI2.IHM2.Controller;

import java.awt.event.*;
import java.time.*;
import java.time.format.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.IHM2.Model.VerificationDateGraphique;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.Bureau;

/**
 * <code>TraitementGraphique</code> est un contrôleur. Il gère le bouton
 * <b>Demander</b> le graphique du Bureau
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class TraitementGraphique implements ActionListener {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JPanel centre;

    /**
     * Constructeur qui permer d'accéder à la fenêtre par la suite
     * 
     * @param fenetre                   la fenetre
     * @param grandLivreDOrAPISeulement modèle non persistant de Réservation
     * @param centre                    le centre de la fenetre
     */
    public TraitementGraphique(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, JPanel centre) {
        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.centre = centre;
    }

    /**
     * Récupère des informations supplémentaires pour ensuite créer le graphique
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String input = JOptionPane.showInputDialog(this.fenetre,
                    "Veuillez entrer la première date afin d'obtenir le graphique. (JJ/MM/AAAA)");
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                LocalDate dateFin = LocalDate.parse(input, inputFormat);
                Object[] options = { "1 sem", "1 mois", "3 mois", "Annuler" };
                int retour = JOptionPane.showOptionDialog(this.fenetre,
                        "Sur quelle durée voulez vous faire le graphique ? ", "Choix de la durée",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                if (retour == 0 || retour == 1 || retour == 2) {
                    LocalDate dateDeb = null;
                    if (retour == 0) {
                        dateDeb = dateFin.minusWeeks(1);
                    } else if (retour == 1) {
                        dateDeb = dateFin.minusMonths(1);
                    } else if (retour == 2) {
                        dateDeb = dateFin.minusMonths(3);
                    }
                    VerificationDateGraphique verif = new VerificationDateGraphique(this.fenetre,
                            this.grandLivreDOrAPISeulement, dateFin, dateDeb, retour, this.centre);
                    verif.run();
                } else {
                    Bureau bureau = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
                    bureau.run();
                }
            } catch (NullPointerException f) {
                Bureau bur = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
                bur.run();
            }
        } catch (DateTimeParseException f) {
            JOptionPane.showMessageDialog(this.fenetre, "Le format de la date n'est pas correct.");
            Bureau bur = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
            bur.run();
        }
    }
}
